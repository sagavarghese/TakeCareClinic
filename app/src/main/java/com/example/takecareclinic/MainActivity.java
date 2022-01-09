package com.example.takecareclinic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TimerTask task= new TimerTask(){

            @Override
            public void run() {
                finish();
                startActivity(new Intent(MainActivity.this, ChooseLogin.class));
            }
        };
        Timer opening = new Timer();
        opening.schedule(task,3000);


        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("MAIN ACTIVITY");
    }
}
