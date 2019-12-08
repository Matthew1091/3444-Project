package com.example.bulbbeats;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class LaunchActivity extends AppCompatActivity implements fftListener{

    private static Context context;
    private ProjectSettings projSet;
    private AudioProcessor audProc;
    private Button playButton;
    private Button stopButton;
    private MediaPlayer mPlayer;
    private TextView songTitle;
    private String temp;
    private static final int MAX_HUE=65535;
    private Messenger message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        context = getApplicationContext();

        //this may be redundant but it's just to make sure it is not used before it is created.
        mPlayer = null;
        audProc=null;
        //constructs the textview.
        songTitle = (TextView)findViewById(R.id.songTitle);

        //grabs the project settings passed by the previous activity.
        projSet = getIntent().getParcelableExtra("settings");

        //start the HueSDK. Bridge should already be connected.
        projSet.phHueSDK = PHHueSDK.create();
        message = new Messenger(projSet.phHueSDK);

            PHBridge bridge = projSet.phHueSDK.getSelectedBridge();
        if((bridge != null)  ) {
            List<PHLight> allLights = bridge.getResourceCache().getAllLights();
            Random rand = new Random();

            for (PHLight light : allLights) {
                PHLightState lightState = new PHLightState();
                lightState.setHue(rand.nextInt(MAX_HUE));
                // To validate your lightstate is valid (before sending to the bridge) you can use:
                // String validState = lightState.validateState();
                bridge.updateLightState(light, lightState, listener);
                //  bridge.updateLightState(light, lightState);   // If no bridge response is required then use this simpler form.
            }

        }
        //cursor is used to get the name of the song. We should find a way to prevent the
        //length of the song from affecting the scale of the buttons. It currently does that.
        Cursor returnCursor = getContentResolver().query(projSet.songUri, null, null, null, null);

        //used to find the song name
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

        //dont know what this does. Crashes without it.
        returnCursor.moveToFirst();

        //actually sets the text view with the song name
        songTitle.setText(returnCursor.getString(nameIndex));

        playButton = findViewById(R.id.playButton);
        stopButton = findViewById(R.id.stopButton);
        setSongOnClickListeners();
        //Control back button press
    }
    PHLightListener listener = new PHLightListener() {

        @Override
        public void onSuccess() {
        }

        @Override
        public void onStateUpdate(Map<String, String> arg0, List<PHHueError> arg1) {
            //  Log.w(TAG, "Light has updated");
        }

        @Override
        public void onError(int arg0, String arg1) {}

        @Override
        public void onReceivingLightDetails(PHLight arg0) {}

        @Override
        public void onReceivingLights(List<PHBridgeResource> arg0) {}

        @Override
        public void onSearchComplete() {}
    };

    //play creates a media player and an audio processor. Also changes the icon image.
    public  void play(View v)
    {
        if(mPlayer == null) {
            mPlayer = MediaPlayer.create(context, projSet.songUri);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    System.out.println("Stahp");
                    stop();
                }
            });
        }
        if(audProc==null) {
            audProc = new AudioProcessor(mPlayer, context, message);
            audProc.setfftListener(this);
        }
        start();

        //make pause button icon drawable
        changePausePlayButton(0);
    }

    public  void pause(View v)
    {
        release();
        changePausePlayButton(1);
    }

    public void stop()
    {
        stopPlayer();
        audProc = null;
        changePausePlayButton(1);
    }

    public void release()
    {
        if(mPlayer != null) {
            mPlayer.pause();
        }
        if(audProc != null) {
            audProc.release();
            audProc = null;
        }
    }

    public void start()
    {
        mPlayer.start();
        audProc.enable(); //actually enables the visualizer to start capturing data.
    }

    //stopPlayer calls release but also frees system resources.
    public  void stopPlayer()
    {
        if(mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            release();
        }
        if(audProc != null)
        {
            audProc.disable();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause(findViewById(android.R.id.content));
    }

    @Override
    protected void onResume() {
        super.onResume();
        pause(findViewById(android.R.id.content));
    }

    @Override
    protected void onStop() {
        super.onStop();
        pause(findViewById(android.R.id.content));
    }
    protected void changePausePlayButton(int isPlay){
        //Int isPlay is 1 if it needs to be the play button or 0 if it needs to be the pause

        if(isPlay==1){
            Drawable resImg = this.getResources().getDrawable(R.drawable.ic_play);

            playButton.setBackground(resImg);
        }
        else{
            Drawable resImg = this.getResources().getDrawable(R.drawable.ic_pause);

            playButton.setBackground(resImg);
        }

    }

    //Overrides back button press to send it to the main page
    @Override
    public void onBackPressed(){
        onPause();
        Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
        startActivity(intent); //will trigger the intent
    }

    private void setSongOnClickListeners(){
        playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playPauseHandler(v);
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopHandler(v);
            }
        });
    }

    private void stopHandler(View v){
        changePausePlayButton(1);
        stop();

    }

    private void playPauseHandler(View v){
        if(audProc == null || !mPlayer.isPlaying()) {
            play(v);
        }
        else if(mPlayer.isPlaying()){
            pause(v);
        }
        else{
            play(v);
        }
    }

    @Override
    public void onUpdate(float[] FFT) {
        //send info to bridge or messenger.

    }
}
