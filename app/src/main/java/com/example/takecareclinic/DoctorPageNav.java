package com.example.takecareclinic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorPageNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private ActionBar ab;
        public final static String MESSAGE_KEY = "message_key";
        public final static String MESSAGE_KEY2 = "message_key2";
        String m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_page_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MESSAGE_KEY);
        m=message;


        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("DOCTOR PAGE NAV");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doctor_page_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        //PERSONAL DETAILS
        if (id == R.id.nav_MyDetails)
        {
            Intent intent = new Intent(DoctorPageNav.this, DoctorForm.class);
            intent.putExtra(MESSAGE_KEY2, m);
            startActivity(intent);
        }

        //SET SCHEDULE
        else if (id == R.id.nav_SetSchedule)
        {
            startActivity(new Intent(DoctorPageNav.this,DoctorSchedule.class));
        }

        //CHECK APPOINTMENTS
        else if (id == R.id.nav_CheckAppointments)
        {
            Intent intent = new Intent(DoctorPageNav.this, DoctorCheckAppointment.class);
            intent.putExtra(MESSAGE_KEY2, m);
            startActivity(intent);
        }
        else if (id == R.id.nav_viewPatientQuestions)
        {
            Intent intent = new Intent(DoctorPageNav.this, Patient_Questions.class);
            intent.putExtra(MESSAGE_KEY2, m);
            startActivity(intent);
        }

        //GO TO HOME PAGE
        else if (id == R.id.nav_logout)
        {
            startActivity(new Intent(DoctorPageNav.this, ChooseLogin.class));
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
