package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThemeActivity extends AppCompatActivity {
    private Button selThemebtn;
    private ProjectSettings projset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        projset = getIntent().getParcelableExtra("settings");

        initThemeWidgits();
        setThemeOnclickListener();
    }

    public void initThemeWidgits(){
        selThemebtn = (Button) findViewById(R.id.themeBtn);
    }

    public void setThemeOnclickListener(){
        selThemebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //will start from themectivity to launchactivity class
                Intent sintent = new Intent(ThemeActivity.this, LaunchActivity.class);
                sintent.putExtra("settings", projset);
                startActivity(sintent); //will trigger the intent
            }
        });
    }


}
