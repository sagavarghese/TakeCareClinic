package com.example.takecareclinic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class AdminPageNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private MyDatabase db;
        private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("ADMIN PAGE NAV");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_page_nav, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        //REGISTER DOCTOR
        if (id == R.id.nav_RegisterDoctor)
        {
            Intent registerIntent = new Intent(AdminPageNav.this, DoctorRegister.class);
            startActivity(registerIntent);
        }

        //VIEW DOCTOR
        else if (id == R.id.nav_ViewDoctors)
        {
                            Cursor res = db.getAllDataP();
                            if (res.getCount() == 0) {
                                showMessage("Error", "Nothing found");
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Id: " + res.getString(0));
                                buffer.append("  username: " + res.getString(1) + "\n");
                            }
                            showMessage("Data", buffer.toString());
        }


        //DELETE DOCTOR
        else if (id == R.id.nav_DeleteDoctor)
        {
            Intent registerIntent = new Intent(AdminPageNav.this, DeleteDoctor.class);
            startActivity(registerIntent);
        }

        //VIEW PATIENTS
        else if (id == R.id.nav_ViewPatiets)
        {
                            Cursor res = db.getAllPatient();
                            if (res.getCount() == 0) {
                                showMessage("Error", "Nothing found");
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Id: " + res.getString(0));
                                buffer.append("  username: " + res.getString(1) + "\n");
                            }
                            showMessage("Data", buffer.toString());
                        }

         //DELETE PATIENTS
        else if (id == R.id.nav_DeletePatients)
        {
            Intent registerIntent = new Intent(AdminPageNav.this, PatientDelete.class);
            startActivity(registerIntent);
        }

        //GOTO HOME PAGE
        else if (id == R.id.logout)
        {
            Intent registerIntent = new Intent(AdminPageNav.this, ChooseLogin.class);
            startActivity(registerIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
