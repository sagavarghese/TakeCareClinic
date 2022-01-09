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

public class PatientForm extends AppCompatActivity {
    private MyDatabase db;
    EditText name,age,bg,cn,address;
    Button submit,view,update;
    Spinner sex;
    private ActionBar ab;
    public final static String MESSAGE_KEY2 ="message_key2";
    String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("PATIENT FORM");

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();


        Intent intent = getIntent();
        final String message2 = intent.getStringExtra(MESSAGE_KEY2);
        m=message2;


        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        sex = (Spinner) findViewById(R.id.spinsex);
        bg = (EditText) findViewById(R.id.bloodGroup);
        address = (EditText) findViewById(R.id.address);
        cn = (EditText) findViewById(R.id.contactNo);


        try {
            Cursor cursor = rdb.rawQuery("select * from patientform where PATIENTJOINID='"+m+"'",null);

            if (cursor != null) {
                cursor.moveToFirst();
                name.setText(cursor.getString(2));
                age.setText(cursor.getString(3));
                bg.setText(cursor.getString(5));
                address.setText(cursor.getString(6));
                cn.setText(cursor.getString(7));
                String chksex=cursor.getString(4);
                if(chksex.equals("Male"))
                {sex.setSelection(0);}
                if(chksex.equals("Female"))
                {sex.setSelection(1);}
                if(chksex.equals("Other"))
                {sex.setSelection(2);}


            }
        } catch (Exception ex) { }





        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name == null || age == null || sex == null || bg == null || address == null || cn==null ||
                        name.length() == 0 || age.length() == 0 || bg.length() == 0 || address.length() == 0 || cn.length() == 0  ) {
                    Toast.makeText(PatientForm.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Cursor cursor = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                    if(cursor != null)
                        cursor.moveToLast();
                    String loginid = cursor.getString(cursor.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                    Log.d("LoginID",loginid);
                    cursor.close();
                    ContentValues values = new ContentValues();
                    values.put(Entries.COLUMN_NAME_NAMEP, name.getText().toString());
                    values.put(Entries.COLUMN_NAME_PATIENTJOINID,loginid);
                    values.put(Entries.COLUMN_NAME_AGEP, age.getText().toString());
                    values.put(Entries.COLUMN_NAME_SEX, sex.getSelectedItem().toString());
                    values.put(Entries.COLUMN_NAME_BLOODGROUP, bg.getText().toString());
                    values.put(Entries.COLUMN_NAME_ADDRESSS, address.getText().toString());
                    values.put(Entries.COLUMN_NAME_CONNO, cn.getText().toString());
                    long check = wdb.insert(Entries.TABLE_NAME2, null, values);
                    if(check>0)
                    {
                        Toast.makeText(PatientForm.this,"YOUR DETAILS ARE SUBMITTED",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(PatientForm.this,"NOT SUBMITTED",Toast.LENGTH_SHORT).show();
                    }

                }}
        });

        Button bUpdate = (Button) findViewById(R.id.update);
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.name);
                age = (EditText) findViewById(R.id.age);
                sex = (Spinner) findViewById(R.id.spinsex);
                bg = (EditText) findViewById(R.id.bloodGroup);
                address = (EditText) findViewById(R.id.address);
                cn = (EditText) findViewById(R.id.contactNo);
                ContentValues values = new ContentValues();
                Cursor cursor = rdb.query(Entries.TABLE_NAMEP, new String[]{Entries.COLUMN_NAME_ID_P}, null, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToLast();
                    String loginid = cursor.getString(cursor.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                    Log.d("LoginID", loginid);
                    values.put(Entries.COLUMN_NAME_PATIENTJOINID, loginid);
                    cursor.close();
                }
                values.put(Entries.COLUMN_NAME_NAMEP, name.getText().toString());

                values.put(Entries.COLUMN_NAME_AGEP, age.getText().toString());
                values.put(Entries.COLUMN_NAME_SEX, sex.getSelectedItem().toString());
                values.put(Entries.COLUMN_NAME_BLOODGROUP, bg.getText().toString());
                values.put(Entries.COLUMN_NAME_ADDRESSS, address.getText().toString());
                values.put(Entries.COLUMN_NAME_CONNO, cn.getText().toString());


                String selection = Entries.COLUMN_NAME_PATIENTJOINID + " = ?";
                String[] selectionArgs = {m};

                long chk = wdb.update(
                        Entries.TABLE_NAME2,
                        values,
                        selection,
                        selectionArgs);
                if (chk > 0) {
                    Toast.makeText(PatientForm.this, "YOUR DETAILS ARE UPDATED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PatientForm.this, "NOT UPDATED", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button view = (Button) findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PatientForm.this,PatientPageNav.class);
                startActivity(i);
            }
        });

    }




}






