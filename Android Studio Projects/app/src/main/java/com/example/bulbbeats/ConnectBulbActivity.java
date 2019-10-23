package com.example.bulbbeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class ConnectBulbActivity extends AppCompatActivity {

    private Button connectBulbbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_bulb);

        initConnectBulbWidgits();
        setConnectBulbOnClickListeners();
    }

    private void initConnectBulbWidgits(){
        connectBulbbtn = (Button) findViewById((R.id.connectBtn));
    }

    public void setConnectBulbOnClickListeners(){

        connectBulbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConnectBulbActivity.this,"clicked", Toast.LENGTH_LONG).show();
                Intent cintent = new Intent(ConnectBulbActivity.this, SongActivity.class);
                startActivity(cintent);

            }
        });
    }



}




