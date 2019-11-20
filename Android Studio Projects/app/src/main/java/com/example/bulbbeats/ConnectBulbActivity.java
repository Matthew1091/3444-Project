package com.example.bulbbeats;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ConnectBulbActivity extends AppCompatActivity {

    static int BRIDGECONNECTED_RCODE = 50;

    private Button connectBulbbtn;
    private Button findBulbbtn;
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
        initFindBulbWidgits();
        setConnectBulbOnClickListeners();
        setFindBulbOnClickListeners();
    }

    private void initConnectBulbWidgits(){
        connectBulbbtn = (Button) findViewById((R.id.connectBtn));
    }

    private void initFindBulbWidgits(){
        findBulbbtn = findViewById((R.id.connectScannerBtn));
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

    private void setFindBulbOnClickListeners(){
        findBulbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FindIntent = new Intent(ConnectBulbActivity.this, PHHomeActivity.class);
                startActivityForResult(FindIntent, BRIDGECONNECTED_RCODE);
            }
        });
    }

    private void connectBulb(Bulb b){
        bulbs.add(b);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == BRIDGECONNECTED_RCODE && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Log.v("LOGV", "onActivityResult: BRIDGECONNECTED");
        }
    }
}
