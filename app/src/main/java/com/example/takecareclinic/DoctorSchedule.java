package com.example.takecareclinic;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DoctorSchedule extends AppCompatActivity {

    int mYear,mMonth,mDay;
    String S;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8;
    private MyDatabase db;
    Button selectDate;
    private ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_schedule);

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("DOCTOR SCHEDULE");

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        cb1=(CheckBox)findViewById(R.id.checkBox);
        cb2=(CheckBox)findViewById(R.id.checkBox2);
        cb3=(CheckBox)findViewById(R.id.checkBox3);
        cb4=(CheckBox)findViewById(R.id.checkBox4);
        cb5=(CheckBox)findViewById(R.id.checkBox5);
        cb6=(CheckBox)findViewById(R.id.checkBox6);
        cb7=(CheckBox)findViewById(R.id.checkBox7);
        cb8=(CheckBox)findViewById(R.id.checkBox8);

        Button b=(Button)findViewById(R.id.done);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    ContentValues values = new ContentValues();
                    Cursor cursor = rdb.query(Entries.TABLE_NAME1 , new String[] {Entries.COLUMN_NAME_ID1},null,null,null,null,null);
                    if(cursor != null)
                        cursor.moveToLast();
                    String formid = cursor.getString(cursor.getColumnIndex(Entries.COLUMN_NAME_ID1));
                    cursor.close();
                    values.put(Entries.COLUMN_NAME_DOCTORFORMID,formid);
                    values.put(Entries.COLUMN_NAME_DATE,selectDate.getText().toString());

                    if(cb1.isChecked())
                    {
                        S = cb1.getText().toString();
                        values.put(Entries.COLUMN_NAME_TIME1,S);

                    }
                    else
                    {
                        values.put(Entries.COLUMN_NAME_TIME1,0);
                    }
                    if(cb2.isChecked())
                    {
                        S = cb2.getText().toString();
                        values.put(Entries.COLUMN_NAME_TIME2,S);

                    }
                    else
                    {
                        values.put(Entries.COLUMN_NAME_TIME2,0);
                    }
                    if(cb3.isChecked())
                    { S = cb3.getText().toString();
                        values.put(Entries.COLUMN_NAME_TIME3,S); }
                    else
                    {
                        values.put(Entries.COLUMN_NAME_TIME3,0);
                    }

                    if(cb4.isChecked())
                    { S = cb4.getText().toString();
                        values.put(Entries.COLUMN_NAME_TIME4,S); }
                    else
                    {
                        values.put(Entries.COLUMN_NAME_TIME4,0);
                    }

                    if(cb5.isChecked())
                    { S = cb5.getText().toString();
                        values.put(Entries.COLUMN_NAME_TIME5,S); }
                    else
                    {
                        values.put(Entries.COLUMN_NAME_TIME5,0);
                    }

                    if(cb6.isChecked())
                    { S = cb6.getText().toString();
                        values.put(Entries.COLUMN_NAME_TIME6,S); }
                    else
                    {
                        values.put(Entries.COLUMN_NAME_TIME6,0);
                    }

                    if(cb7.isChecked())
                    { S = cb7.getText().toString();
                        values.put(Entries.COLUMN_NAME_TIME7,S); }
                    else
                    {
                        values.put(Entries.COLUMN_NAME_TIME7,0);
                    }

                    if(cb8.isChecked())
                    { S = cb8.getText().toString();
                        values.put(Entries.COLUMN_NAME_TIME8,S); }
                    else
                    {
                        values.put(Entries.COLUMN_NAME_TIME8,0);
                    }
                   long ch= wdb.insert(Entries.TABLE_NAMEDS, null, values);

                    if(ch>0)
                    {
                        Toast.makeText(DoctorSchedule.this,"SCHEDULE UPDATED",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DoctorSchedule.this,"NOT UPDATED",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        Button viewDS=(Button)findViewById(R.id.view);
        viewDS.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DoctorSchedule.this,DoctorPageNav.class);
                        startActivity(i);
                    }
                }
        );


        selectDate = (Button) findViewById(R.id.selectDate);
        selectDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(DoctorSchedule.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "dd/MM/yy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                        selectDate.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });
    }

}
