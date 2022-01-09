package com.example.takecareclinic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText username,password;
    Button btn,btn1;
    private ActionBar ab;
    ImageButton homePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("ADMIN LOGIN");

        username = (EditText)findViewById(R.id.dUsername);
        password = (EditText) findViewById(R.id.dPassword);


        homePage=(ImageButton)findViewById(R.id.imgbtnHome);
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(AdminLogin.this, ChooseLogin.class);
                startActivity(home);
            }
        });

        //LOGIN BUTTON
        btn = (Button)findViewById(R.id.dLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  user=username.getText().toString();
                String pwd=password.getText().toString();

                if(user.equals("admin") && pwd.equals("admin"))
                {
                    Toast.makeText(AdminLogin.this,"LOGGEDIN",Toast.LENGTH_SHORT).show();
                    Intent intee= new Intent(AdminLogin.this,AdminPageNav.class);
                    startActivity(intee);
                }
                else
                {
                    Toast.makeText(AdminLogin.this,"LOGIN ERROR",Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}
