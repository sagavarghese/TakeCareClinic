package com.example.takecareclinic;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorRegister extends AppCompatActivity {
    EditText docUsername,docPassword;
    Button docRegister,back;
    private MyDatabase db;
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("DOCTOR REGISTER");

        docUsername =(EditText)findViewById(R.id.docUsername);
        docPassword=(EditText)findViewById(R.id.docPassword);



        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent=new Intent(DoctorRegister.this,AdminPageNav.class);
                startActivity(LoginIntent); }});



        docRegister=(Button)findViewById(R.id.docRegister);
        docRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eUN = (EditText)findViewById(R.id.docUsername);
                EditText ePW = (EditText)findViewById(R.id.docPassword);

                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_UN, eUN.getText().toString());
                values.put(Entries.COLUMN_NAME_PW, ePW.getText().toString());
                long check = wdb.insert(Entries.TABLE_NAMED, null, values);

                if(check>0)
                {
                    Toast.makeText(DoctorRegister.this,"DOCTOR REGISTERED",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(DoctorRegister.this,"DOCTOR NOT REGISTERED",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
