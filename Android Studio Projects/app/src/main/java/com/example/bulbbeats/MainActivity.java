package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button newProjectbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Malena    hi*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMainWidgits();
        setOnClickListeners();

    }

    private void initMainWidgits(){
        newProjectbtn = findViewById((R.id.button));
    }

    private void setOnClickListeners(){
        newProjectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //will start from mainactivity to connectbulbactivity class
                Intent intent = new Intent(MainActivity.this, ConnectBulbActivity.class);
                startActivity(intent); //will trigger the intent
            }
        });
    }
}
