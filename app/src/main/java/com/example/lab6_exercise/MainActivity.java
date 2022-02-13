package com.example.lab6_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartServiceClicked(View view) {
        Intent intent = new Intent(MainActivity.this, DemoService.class);
        startService(intent);
    }

    public void onShowNameClicked(View view) {
        TextView textView = findViewById(R.id.name);
        textView.setText(getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE).getString("name", "Name not ready yet. "));
    }
}