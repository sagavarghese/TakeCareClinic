package com.example.takecareclinic;

import android.content.ContentValues;
import android.content.Intent;
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

public class PatientRegister extends AppCompatActivity {
    EditText docUsername;
    EditText docPassword;
    Button docRegister,back;
    ImageButton home;
    private MyDatabase db;
    private ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("PATIENT REGISTER");

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        docUsername =(EditText)findViewById(R.id.docUsername);
        docPassword=(EditText)findViewById(R.id.docPassword);


        docRegister=(Button)findViewById(R.id.docRegister);
        home=(ImageButton)findViewById(R.id.imgbtnHome);
        back = (Button) findViewById(R.id.back);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent=new Intent(PatientRegister.this,ChooseLogin.class);
                startActivity(LoginIntent); }});

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent=new Intent(PatientRegister.this,PatientLogin.class);
                startActivity(LoginIntent); }});

        docRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=docUsername.getText().toString().trim();
                String pwd=docPassword.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_UN_P, user);
                values.put(Entries.COLUMN_NAME_PW_P, pwd);
                long check = wdb.insert(Entries.TABLE_NAMEP, null, values);
                if(check>0)
                {
                    Toast.makeText(PatientRegister.this,"YOU ARE REGISTERED",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(PatientRegister.this,"YOU ARE NOT REGISTERED",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}




