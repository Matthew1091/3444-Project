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
import android.widget.Button;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity {

    private static Context context;
    private ProjectSettings projSet;
    private AudioProcessor audProc;
    private Button playButton;
    private MediaPlayer mPlayer;
    private TextView songTitle;
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        checkPermissions();
        context = getApplicationContext();

        //this may be redundant but it's just to make sure it is not used before it is created.
        mPlayer = null;

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

        setSongOnClickListeners();
    }

    /*
        play creates a media player and an audio processor. Also changes the icon image.
     */
    private void checkPermissions(){
        //Need to get permissions from the user.
        //Checks if permission is granted
        int PERMISSION_CODE = 1;
        //TODO: add something to handle denied permission request
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            //Permission not granted. Need to ask for it
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.RECORD_AUDIO},1);
        }
    }

    public  void play(View v)
    {
        audProc = new AudioProcessor(projSet, context);
        audProc.start();

        //make pause button icon drawable
        Drawable resImg = this.getResources().getDrawable(R.drawable.ic_pause);

        playButton.setBackground(resImg);
    }

    public  void pause(View v)
    {
        audProc.release();
    }

    public void stop(View v)
    {
        audProc.stopPlayer();
    }

    /*
    stopPlayer calls release but also frees system resources.
     */


    @Override
    protected void onStop() {
        super.onStop();
        audProc.stopPlayer();
    }

    private void setSongOnClickListeners(){
        playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //will start from songactivity to themeactivity class
                play(v);
            }
        });
    }


}
