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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity {

    private static Context context;
    private ProjectSettings projSet;
    private AudioProcessor audProc;
    private Button playButton;
    private Button stopButton;
    private MediaPlayer mPlayer;
    private TextView songTitle;
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        // checkPermissions();
        context = getApplicationContext();

        //this may be redundant but it's just to make sure it is not used before it is created.
        mPlayer = null;
        audProc=null;
        //constructs the textview.
        songTitle = (TextView)findViewById(R.id.songTitle);

        //grabs the project settings passed by the previous activity.
        projSet = getIntent().getParcelableExtra("settings");

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
    }

    /*
        play creates a media player and an audio processor. Also changes the icon image.
     */

    public  void play(View v)
    {
        if(mPlayer == null) {
            mPlayer = MediaPlayer.create(context, projSet.songUri);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                    release();
                }
            });
        }
        if(audProc==null) {
            audProc = new AudioProcessor(mPlayer, context);
        }
        start();

        //make pause button icon drawable
        Drawable resImg = this.getResources().getDrawable(R.drawable.ic_pause);

        playButton.setBackground(resImg);
    }

    public  void pause(View v)
    {
        release();
        Drawable resImg = this.getResources().getDrawable(R.drawable.ic_play);
        playButton.setBackground(resImg);
    }

    public void stop(View v)
    {
        Drawable resImg = this.getResources().getDrawable(R.drawable.ic_play);

        playButton.setBackground(resImg);
        stopPlayer();
        audProc = null;
    }

    /*
    stopPlayer calls release but also frees system resources.
     */
    public void release()
    {
        if(mPlayer != null) {
            mPlayer.pause();
        }
        if(audProc!= null) {
            audProc.release();
            audProc = null;
        }
    }
    public Boolean isPlaying(){
        return mPlayer.isPlaying();
    }
    public void start()
    {
        mPlayer.start();
        audProc.enable();
    }

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
        playPauseHandler(findViewById(android.R.id.content));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isPlaying())
            pause(findViewById(android.R.id.content));
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
                stop(v);
            }
        });
    }

    private void playPauseHandler(View v){
        if(audProc == null){
            System.out.println("Audproc is null");
        }
        if(audProc == null) {
            play(v);
        }
        else if(isPlaying()){
            pause(v);
        }
        else{
            play(v);
        }
    }

}
