package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {
    private static final int OPEN_DOC = 10;

    Button button;
    Button openDoc;
    private ProjectSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        settings = null;

        openDoc = findViewById((R.id.openDoc));
        openDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDocument();
            }
        });

        button = findViewById((R.id.button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDoc();
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
