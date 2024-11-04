package com.example.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private MediaPlayer music;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MusicService", "Service created");
        initializeMediaPlayer();
    }

    private void initializeMediaPlayer() {
        music = MediaPlayer.create(this, R.raw.jaada);
        if (music == null) {
            Log.e("MusicService", "MediaPlayer creation failed");
            return;
        }
        music.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case "PLAY":
                    if (music == null) {
                        initializeMediaPlayer();
                    }
                    if (music != null && !music.isPlaying()) {
                        Log.d("MusicService", "Playing music");
                        music.start();
                    } else {
                        Log.d("MusicService", "Music is already playing or MediaPlayer is null");
                    }
                    break;
                case "PAUSE":
                    if (music != null && music.isPlaying()) {
                        Log.d("MusicService", "Pausing music");
                        music.pause();
                    } else {
                        Log.d("MusicService", "Music is not playing or MediaPlayer is null");
                    }
                    break;
                case "STOP":
                    if (music != null) {
                        Log.d("MusicService", "Stopping music");
                        music.stop();
                        music.release();
                        music = null;
                    } else {
                        Log.d("MusicService", "MediaPlayer is null");
                    }
                    break;
                default:
                    Log.d("MusicService", "Unknown action: " + action);
                    break;
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (music != null) {
            Log.d("MusicService", "Releasing MediaPlayer");
            music.release();
        }
    }
}
