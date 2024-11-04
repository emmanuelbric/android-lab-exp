package com.example.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mplay, mpause, mstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Buttons
        mplay = findViewById(R.id.start);
        mpause = findViewById(R.id.pause);
        mstop = findViewById(R.id.stop);

        // Set Click Listeners for Buttons
        mplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, MusicService.class);
                startIntent.setAction("PLAY");
                startService(startIntent);
            }
        });

        mpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pauseIntent = new Intent(MainActivity.this, MusicService.class);
                pauseIntent.setAction("PAUSE");
                startService(pauseIntent);
            }
        });

        mstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(MainActivity.this, MusicService.class);
                stopIntent.setAction("STOP");
                startService(stopIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent stopIntent = new Intent(MainActivity.this, MusicService.class);
        stopIntent.setAction("STOP");
        startService(stopIntent);
    }
}
