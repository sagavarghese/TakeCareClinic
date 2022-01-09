package com.example.takecareclinic;

import android.content.ContentValues;
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
import android.widget.Spinner;
import android.widget.Toast;

public class DoctorForm extends AppCompatActivity {
    EditText name, age, contact, address;
    Spinner speciality;
    Button submit, back,update;
    private MyDatabase db;
    private ActionBar ab;
    public final static String MESSAGE_KEY2 ="message_key2";
    String m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_form);

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("DOCTOR FORM");

        Intent intent = getIntent();
        final String message2 = intent.getStringExtra(MESSAGE_KEY2);
        m=message2;

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        contact = (EditText) findViewById(R.id.contact);
        address = (EditText) findViewById(R.id.address);
        speciality = (Spinner) findViewById(R.id.spinner);

        try {
            Cursor cursor = rdb.rawQuery("select * from doctorform where DOCTORJOINID='"+m+"'",null);

            if (cursor != null) {
                cursor.moveToFirst();
                name.setText(cursor.getString(2));
                age.setText(cursor.getString(3));
                contact.setText(cursor.getString(4));
                address.setText(cursor.getString(5));
                String chksex=cursor.getString(6);
                if(chksex.equals("DERMATOLOGY"))
                {speciality.setSelection(0);}
                if(chksex.equals("DIAGNOSTIC RADIOLOGY"))
                {speciality.setSelection(1);}
                if(chksex.equals("NEUROLOGY"))
                {speciality.setSelection(2);}
                if(chksex.equals("DIAGNOSTIC RADIOLOGY"))
                {speciality.setSelection(3);}
                if(chksex.equals("PSYCHIATRY"))
                {speciality.setSelection(4);}


            }
        } catch (Exception ex) { }



        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DoctorForm.this,DoctorPageNav.class);
                        startActivity(i);

                    }
                }
        );


        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name == null || age == null || contact == null || address == null || speciality == null ||
                        name.length() == 0 || age.length() == 0 || contact.length() == 0 || address.length() == 0  ) {
                    Toast.makeText(DoctorForm.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    Cursor cursor = rdb.query(Entries.TABLE_NAMED , new String[] {Entries.COLUMN_NAME_ID},null,null,null,null,null);
                    if(cursor != null)
                        cursor.moveToLast();
                    String formid = cursor.getString(cursor.getColumnIndex(Entries.COLUMN_NAME_ID));
                    cursor.close();
                    ContentValues values = new ContentValues();
                    values.put(Entries.COLUMN_NAME_DOCTORJOINID,formid);
                    values.put(Entries.COLUMN_NAME_NAME, name.getText().toString());
                    values.put(Entries.COLUMN_NAME_AGE, age.getText().toString());
                    values.put(Entries.COLUMN_NAME_CONTACT, contact.getText().toString());
                    values.put(Entries.COLUMN_NAME_ADDRESS, address.getText().toString());
                    values.put(Entries.COLUMN_NAME_SPECIALITY, speciality.getSelectedItem().toString());
                    long check = wdb.insert(Entries.TABLE_NAME1, null, values);
                    if(check>0)
                    {
                        Toast.makeText(DoctorForm.this,"YOUR DETAILS ARE SUBMITTED",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DoctorForm.this,"NOT SUBMITTED",Toast.LENGTH_SHORT).show();
                    }

                }}
        });




        Button bUpdate = (Button) findViewById(R.id.update);
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.name);
                age = (EditText) findViewById(R.id.age);
                contact = (EditText) findViewById(R.id.contact);
                address = (EditText) findViewById(R.id.address);
                speciality = (Spinner) findViewById(R.id.spinner);


                Cursor cursor = rdb.query(Entries.TABLE_NAME1 , new String[] {Entries.COLUMN_NAME_ID1},null,null,null,null,null);
                if(cursor != null)
                    cursor.moveToLast();
                String formid = cursor.getString(cursor.getColumnIndex(Entries.COLUMN_NAME_ID1));
                cursor.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_DOCTORJOINID,formid);
                values.put(Entries.COLUMN_NAME_NAME, name.getText().toString());
                values.put(Entries.COLUMN_NAME_AGE, age.getText().toString());
                values.put(Entries.COLUMN_NAME_CONTACT, contact.getText().toString());
                values.put(Entries.COLUMN_NAME_ADDRESS, address.getText().toString());
                values.put(Entries.COLUMN_NAME_SPECIALITY, speciality.getSelectedItem().toString());


                String selection = Entries.COLUMN_NAME_DOCTORJOINID + " = ?";
                String[] selectionArgs = {m};

                long chk = wdb.update(
                        Entries.TABLE_NAME1,
                        values,
                        selection,
                        selectionArgs);
                if (chk > 0) {
                    Toast.makeText(DoctorForm.this, "YOUR DETAILS ARE UPDATED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DoctorForm.this, "NOT UPDATED", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}



















