package com.example.takecareclinic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ChooseLogin extends AppCompatActivity {
    ImageButton doctor,patient,admin;
    private ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login);

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("CHOOSE LOGIN");

         admin = (ImageButton) findViewById(R.id.imgbtnAdmin);
         doctor = (ImageButton) findViewById(R.id.imgbtnDoctor);
         patient = (ImageButton) findViewById(R.id.imgbtnPatient);

         //ADMIN
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseLogin.this,AdminLogin.class);
                startActivity(i);
            }
        });

        //DOCTOR
       doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseLogin.this,DoctorLogin.class);
                startActivity(i);
            }
        });

       //PATIENT
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseLogin.this,PatientLogin.class);
                startActivity(i);
            }
        });


    }
}
