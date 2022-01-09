package com.example.takecareclinic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DoctorLogin extends AppCompatActivity {
    EditText dUsername,dPassword;
    Button dLogin,homePage;
    private MyDatabase db;
    private ActionBar ab;
    String match;
    ImageButton homeP;
    public final static String MESSAGE_KEY = "message_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("DOCTOR LOGIN");

        dUsername =(EditText)findViewById(R.id.dUsername);
        dPassword =(EditText)findViewById(R.id.dPassword);

        dLogin=(Button)findViewById(R.id.dLogin);




        homeP=(ImageButton)findViewById(R.id.imgbtnHome);
        homeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(DoctorLogin.this, ChooseLogin.class);
                startActivity(home);
            }
        });


        dLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Cursor cursorr = rdb.rawQuery("select * from doctorRegister where UN='"+dUsername.getText().toString()+"'",null);
                if (cursorr.moveToFirst())
                {
                    do
                    {    match= cursorr.getString(cursorr.getColumnIndex("ID"));

                    }while (cursorr.moveToNext());
                }


                String user = dUsername.getText().toString();
                String pwd = dPassword.getText().toString();


                Boolean res = db.loginDoctor(user, pwd);
                if (res == true) {

                    Toast.makeText(DoctorLogin.this,"LOGGEDIN",Toast.LENGTH_SHORT).show();
                    Intent intee = new Intent(DoctorLogin.this, DoctorPageNav.class);
                    intee.putExtra(MESSAGE_KEY, match);
                    startActivity(intee);
                } else {
                    Toast.makeText(DoctorLogin.this, "LOGIN ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
