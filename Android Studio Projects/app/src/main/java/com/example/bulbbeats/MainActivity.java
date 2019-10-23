package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
     Button newProject;
    private ProjectSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Malena    hi*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newProject = findViewById((R.id.newProject));
        newProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSongActivity();
            }
        });
    }

    private void openSongActivity() {
        Intent intent = new Intent(this, SongActivity.class);
        startActivity(intent);
    }

    private void openActivity2() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
