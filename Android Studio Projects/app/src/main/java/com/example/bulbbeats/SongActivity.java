package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;



public class SongActivity extends AppCompatActivity {

    private static final int OPEN_DOC = 10;

    private Button button;
    private Button openDoc;
    private ProjectSettings settings;

    private Button Uploadbtn;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(SongActivity.this,"got here", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        initSongWidgits();
        setSongOnClickListeners();

    }
    private void initSongWidgits(){
        Uploadbtn = (Button) findViewById(R.id.uploadBtn);
        openDoc = findViewById((R.id.openDoc));
        button = findViewById((R.id.button));
    }

    private void setSongOnClickListeners(){
        openDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDocument();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDoc();
            }
        });
        Uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //will start from songactivity to themeactivity class
                Intent sintent = new Intent(SongActivity.this, ThemeActivity.class);
                startActivity(sintent); //will trigger the intent
            }
        });
    }

    public void openDocument() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        startActivityForResult(intent, OPEN_DOC);
    }

    public void uploadDoc() {
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.putExtra("openDoc", settings);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == OPEN_DOC){
            Uri uri = data.getData();
            settings = new ProjectSettings(uri);
            openDoc.setText(settings.songUri.getPath());
        }
    }
}
