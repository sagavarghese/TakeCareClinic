package com.example.takecareclinic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PatientLogin extends AppCompatActivity {
    private MyDatabase db;
    EditText dUsername;
    EditText dPassword;
    Button dLogin,signup;
    ImageButton homePage;
    private ActionBar ab;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();
        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("PATIENT LOGIN");

        dUsername =(EditText)findViewById(R.id.dUsername);
        dPassword =(EditText)findViewById(R.id.dPassword);


        signup=(Button)findViewById(R.id.dHome);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(PatientLogin.this, PatientRegister.class);
                startActivity(registerIntent);
            }
        });

        homePage=(ImageButton)findViewById(R.id.imgbtnHome);
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(PatientLogin.this, ChooseLogin.class);
                startActivity(home);
            }
        });

        dLogin=(Button)findViewById(R.id.dLogin);
        dLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase rdb = db.getReadableDatabase();
                String user = dUsername.getText().toString();
                String pwd = dPassword.getText().toString();
                Boolean res = db.loginPatient(user, pwd);

                Cursor cursor = rdb.rawQuery("select IDP from patientRegister where UNP = ?",new String[] {user});
                if(cursor.moveToFirst()) {
                    id= cursor.getString(0);
                }
                cursor.close();
                if (res == true) {

                    Toast.makeText(PatientLogin.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent intee = new Intent(PatientLogin.this, PatientPageNav.class);
                    intee.putExtra("Username",user);
                    intee.putExtra("Id",id);
                    startActivity(intee);
                } else {
                    Toast.makeText(PatientLogin.this, "LOGIN ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}





