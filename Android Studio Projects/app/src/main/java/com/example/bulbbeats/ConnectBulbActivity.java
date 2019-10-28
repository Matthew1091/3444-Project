package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ConnectBulbActivity extends AppCompatActivity {

    private Button connectBulbbtn;
    private ProjectSettings projset;
    private ArrayList<Bulb> bulbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_bulb);

        bulbs = new ArrayList<Bulb>(5);

        int ID = 1;
        Bulb tempBulb = new Bulb(ID);
        connectBulb(tempBulb);
        projset = new ProjectSettings(bulbs);

        initConnectBulbWidgits();
        setConnectBulbOnClickListeners();
    }

    private void initConnectBulbWidgits(){
        connectBulbbtn = (Button) findViewById((R.id.connectBtn));
    }

    private void setConnectBulbOnClickListeners(){
        connectBulbbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) { //will start from connectbulb to songactivity class
               Intent cintent = new Intent(ConnectBulbActivity.this, SongActivity.class);
               cintent.putExtra("settings", projset);
               startActivity(cintent); //will trigger the intent
           }
       });
    }

    private void connectBulb(Bulb b){
        bulbs.add(b);
    }
}
