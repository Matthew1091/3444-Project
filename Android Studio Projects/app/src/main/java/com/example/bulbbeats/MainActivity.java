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
      /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });*/
    }

    private void initMainWidgits(){
        newProjectbtn = (Button) findViewById((R.id.button));
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
    /*private void openActivity2() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }*/
}
