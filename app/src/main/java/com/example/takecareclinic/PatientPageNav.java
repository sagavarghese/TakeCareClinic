package com.example.takecareclinic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

public class PatientPageNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBar ab;
    String a,b;
   // public final static String MESSAGE_KEY = "message_key";
    //public final static String MESSAGE_KEY2 = "message_key2";
    String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();
        a =i.getStringExtra("Username");
        b = i.getStringExtra("Id");
        //Log.d("sagaaaaaaaaaaaaaaaaa",b);
        super.onCreate(savedInstanceState);
        setTitle("PATIENT PAGE NAV");
        setContentView(R.layout.activity_patient_page_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        View v = navigationView.getHeaderView(0);
        TextView nam = (TextView)v.findViewById(R.id.nm);
        nam.setText(a);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_page_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_PatientForm)
        {
            Intent intent = new Intent(PatientPageNav.this, PatientForm.class);
            intent.putExtra("Id", m);
            startActivity(intent);
        }
        else if (id == R.id.nav_Appointment)
        {
            startActivity(new Intent(PatientPageNav.this,Patient_Appointment.class));
        }
        else if (id == R.id.nav_CancelAppointment)
        {
            Intent intent = new Intent(PatientPageNav.this, PatientCancelAppointment.class);
            intent.putExtra("Id", m);
            startActivity(intent);
        }
        else if (id == R.id.nav_AskYourDoctor)
        {
            Intent i = new Intent(PatientPageNav.this, Ask_Doctor.class);
            i.putExtra("un",a);
            i.putExtra("iid",b);
            startActivity(i);
        }
        else if (id == R.id.nav_ViewAnswer)
        {
            Intent i = new Intent(PatientPageNav.this, ViewAnswer.class);
            i.putExtra("id",b);
            startActivity(i);
        }
        else if (id == R.id.nav_Home)
        {
            startActivity(new Intent(PatientPageNav.this, ChooseLogin.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
