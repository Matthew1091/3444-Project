package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;



public class SongActivity extends AppCompatActivity {

    private Button Uploadbtn;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        initSongWidgits();
        setSongOnClickListeners();

    }
    private void initSongWidgits(){
        Uploadbtn = (Button) findViewById(R.id.uploadBtn);
    }

    private void setSongOnClickListeners(){
        Uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //will start from songactivity to themeactivity class
                Intent sintent = new Intent(SongActivity.this, ThemeActivity.class);
                startActivity(sintent); //will trigger the intent
            }
        });
    }

}
