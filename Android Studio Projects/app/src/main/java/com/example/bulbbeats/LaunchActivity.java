package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity {

    ImageButton Play;
    TextView songTitle;
    ProjectSettings projSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Intent intent = getIntent();

        projSet = intent.getParcelableExtra("openDoc");

        Play = findViewById((R.id.playBtn));

        /*if(projSet != null) {
            songTitle.setText(projSet.songUri.getLastPathSegment());
        }*/// this code makes it crash for some reason, but projSet does successfully come through.
    }
}
