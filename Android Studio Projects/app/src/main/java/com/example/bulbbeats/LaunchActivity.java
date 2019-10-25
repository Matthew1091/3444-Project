package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity {

    private ProjectSettings projset;
    private Button playButton;
    private MediaPlayer mPlayer;
    private TextView songTitle;
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mPlayer = null;

        songTitle = (TextView)findViewById(R.id.songTitle);

        projset = getIntent().getParcelableExtra("settings");

        Cursor returnCursor = getContentResolver().query(projset.songUri, null, null, null, null);

        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

        returnCursor.moveToFirst();

        songTitle.setText(returnCursor.getString(nameIndex));

        playButton = findViewById(R.id.playButton);

        setSongOnClickListeners();

    }

    public  void play(View v)
    {
        if(mPlayer == null) {
            mPlayer = MediaPlayer.create(this, projset.songUri);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        mPlayer.start();
    }

    public  void pause(View v)
    {
        if(mPlayer != null) {
            mPlayer.pause();
        }
    }

    public void stop(View v)
    {
        stopPlayer();
    }

    public  void stopPlayer()
    {
        if(mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
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
