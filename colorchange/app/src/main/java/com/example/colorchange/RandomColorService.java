package com.example.colorchange;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class RandomColorService extends Service {
    private final IBinder binder = new LocalBinder();
    private final Random random = new Random();

    public class LocalBinder extends Binder {
        RandomColorService getService() {
            return RandomColorService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int generateRandomColor() {
        return 0xff000000 | random.nextInt(0x00ffffff);
    }
}
