package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    public  void play(View v)
    {
        if(mPlayer == null) {
            mPlayer = MediaPlayer.create(this, projSet.songUri);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                    audProc.release();
                }
            });
            audProc = new AudioProcessor(mPlayer.getAudioSessionId());
        }

        mPlayer.start();

        //make pause button icon drawable
        Drawable resImg = this.getResources().getDrawable(R.drawable.ic_pause);

        playButton.setBackground(resImg);
    }

    public  void pause(View v)
    {
        if(mPlayer != null) {
            mPlayer.pause();
            audProc.release();
        }
    }

    public void stop(View v)
    {
        stopPlayer();
    }

    /*
    stopPlayer calls release but also frees system resources.
     */
    public  void stopPlayer()
    {
        if(mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            audProc.release();
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
