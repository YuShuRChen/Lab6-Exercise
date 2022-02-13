package com.example.lab6_exercise;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoService extends Service {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public DemoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(DemoService.this, "Service started", Toast.LENGTH_SHORT).show();

        executor.submit(() -> {
            synchronized (this) {
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            SharedPreferences preferences = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", "Jane");

            editor.apply();

            stopSelf(startId);
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(DemoService.this, "Service stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}