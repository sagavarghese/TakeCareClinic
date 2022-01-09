package com.example.takecareclinic;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Bookings extends AppCompatActivity {
    public final static String MESSAGE_KEY ="message_key";
    private MyDatabase db;
    String dd;
    String book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);


        setTitle("BOOKING");

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MESSAGE_KEY);
        TextView textView = (TextView)findViewById(R.id.vieww);
        textView.setText(message);

        TextView nm = (TextView) findViewById(R.id.txtDoctor);
        TextView sp = (TextView) findViewById(R.id.txtSpeciality);
        TextView cn = (TextView) findViewById(R.id.txtContact);
        final  TextView dt = (TextView) findViewById(R.id.txtDate);



        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);

        Cursor cursor1 = rdb.rawQuery("select * from doctorform where DOCTORJOINID='"+message+"'",null);
        if (cursor1.moveToFirst())
        {
            do
            {
                String name1= cursor1.getString(cursor1.getColumnIndex("NAME"));
                String contact1 = cursor1.getString(cursor1.getColumnIndex("CONTACT"));
                String speciality1 = cursor1.getString(cursor1.getColumnIndex("SPECIALITY"));
                nm.setText(name1);
                sp.setText(speciality1);
                cn.setText(contact1);

            }while (cursor1.moveToNext());
        }



        Cursor cursor = rdb.rawQuery("select * from doctorSchedule where DOCTORFORMID='"+message+"'",null);
        if (cursor.moveToFirst())
        {
            do
            {   String date1= cursor.getString(cursor.getColumnIndex("DATE"));
                String s1 = cursor.getString(cursor.getColumnIndex("TIME1"));
                String s2 = cursor.getString(cursor.getColumnIndex("TIME2"));
                String s3 = cursor.getString(cursor.getColumnIndex("TIME3"));
                String s4 = cursor.getString(cursor.getColumnIndex("TIME4"));
                String s5 = cursor.getString(cursor.getColumnIndex("TIME5"));
                String s6 = cursor.getString(cursor.getColumnIndex("TIME6"));
                String s7 = cursor.getString(cursor.getColumnIndex("TIME7"));
                String s8 = cursor.getString(cursor.getColumnIndex("TIME8"));
                dt.setText(date1);
                dd=cursor.getString(cursor.getColumnIndex("DATE"));

                if(s1.equals("0"))
                {
                    btn1.setEnabled(false);
                }
                if(s2.equals("0"))
                {
                    btn2.setEnabled(false);
                }
                if(s3.equals("0"))
                {
                    btn3.setEnabled(false);
                }
                if(s4.equals("0"))
                {
                    btn4.setEnabled(false);
                }
                if(s5.equals("0"))
                {
                    btn5.setEnabled(false);
                }
                if(s6.equals("0"))
                {
                    btn6.setEnabled(false);
                }
                if(s7.equals("0"))
                {
                    btn7.setEnabled(false);
                }
                if(s8.equals("0"))
                {
                    btn8.setEnabled(false);
                }

            }while (cursor.moveToNext());
        }











        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Confirmation","Are you sure you want to book appointment at 9:00am to 10:00am");
                book="9:00am to 10:00am";

                Cursor cursor2 = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                if(cursor2 != null)
                    cursor2.moveToLast();
                String loginid = cursor2.getString(cursor2.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                Log.d("LoginID",loginid);
                cursor2.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTAPPJOINID,loginid);
                values.put(Entries.COLUMN_NAME_DOCID, message);
                values.put(Entries.COLUMN_NAME_SETDATE,dd );
                values.put(Entries.COLUMN_NAME_TIME, book);
                wdb.insert(Entries.TABLE_NAME_BOOKING, null, values);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Confirmation","Are you sure you want to book appointment at 10:00am to 11:00am");
                book="10:00am to 11:00am";

                Cursor cursor2 = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                if(cursor2 != null)
                    cursor2.moveToLast();
                String loginid = cursor2.getString(cursor2.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                Log.d("LoginID",loginid);
                cursor2.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTAPPJOINID,loginid);
                values.put(Entries.COLUMN_NAME_DOCID, message);
                values.put(Entries.COLUMN_NAME_SETDATE,dd );
                values.put(Entries.COLUMN_NAME_TIME, book);
                wdb.insert(Entries.TABLE_NAME_BOOKING, null, values);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Confirmation","Are you sure you want to book appointment at 11:00am to 12:00pm");
                book="11:00am to 12:00pm";

                Cursor cursor2 = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                if(cursor2 != null)
                    cursor2.moveToLast();
                String loginid = cursor2.getString(cursor2.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                Log.d("LoginID",loginid);
                cursor2.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTAPPJOINID,loginid);
                values.put(Entries.COLUMN_NAME_DOCID, message);
                values.put(Entries.COLUMN_NAME_SETDATE,dd );
                values.put(Entries.COLUMN_NAME_TIME, book);
                wdb.insert(Entries.TABLE_NAME_BOOKING, null, values);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Confirmation","Are you sure you want to book appointment at 1:00pm to 2:00pm");
                book="1:00pm to 2:00pm";

                Cursor cursor2 = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                if(cursor2 != null)
                    cursor2.moveToLast();
                String loginid = cursor2.getString(cursor2.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                Log.d("LoginID",loginid);
                cursor2.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTAPPJOINID,loginid);
                values.put(Entries.COLUMN_NAME_DOCID, message);
                values.put(Entries.COLUMN_NAME_SETDATE,dd );
                values.put(Entries.COLUMN_NAME_TIME, book);
                wdb.insert(Entries.TABLE_NAME_BOOKING, null, values);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Confirmation","Are you sure you want to book appointment at 2:00pm to 3:00pm");
                book=" 2:00pm to 3:00pm";

                Cursor cursor2 = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                if(cursor2 != null)
                    cursor2.moveToLast();
                String loginid = cursor2.getString(cursor2.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                Log.d("LoginID",loginid);
                cursor2.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTAPPJOINID,loginid);
                values.put(Entries.COLUMN_NAME_DOCID, message);
                values.put(Entries.COLUMN_NAME_SETDATE,dd );
                values.put(Entries.COLUMN_NAME_TIME, book);
                wdb.insert(Entries.TABLE_NAME_BOOKING, null, values);

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Confirmation","Are you sure you want to book appointment at 3:00pm to 4:00pm");
                book="3:00pm to 4:00pm";

                Cursor cursor2 = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                if(cursor2 != null)
                    cursor2.moveToLast();
                String loginid = cursor2.getString(cursor2.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                Log.d("LoginID",loginid);
                cursor2.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTAPPJOINID,loginid);
                values.put(Entries.COLUMN_NAME_DOCID, message);
                values.put(Entries.COLUMN_NAME_SETDATE,dd );
                values.put(Entries.COLUMN_NAME_TIME, book);
                wdb.insert(Entries.TABLE_NAME_BOOKING, null, values);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Confirmation","Are you sure you want to book appointment at 4:00pm to 5:00pm");
                book="4:00pm to 5:00pm";

                Cursor cursor2 = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                if(cursor2 != null)
                    cursor2.moveToLast();
                String loginid = cursor2.getString(cursor2.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                Log.d("LoginID",loginid);
                cursor2.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTAPPJOINID,loginid);
                values.put(Entries.COLUMN_NAME_DOCID, message);
                values.put(Entries.COLUMN_NAME_SETDATE,dd );
                values.put(Entries.COLUMN_NAME_TIME, book);
                wdb.insert(Entries.TABLE_NAME_BOOKING, null, values);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Confirmation","Are you sure you want to book appointment at 5:00pm to 6:00pm");
                book="5:00pm to 6:00pm";

                Cursor cursor2 = rdb.query(Entries.TABLE_NAMEP , new String[] {Entries.COLUMN_NAME_ID_P},null,null,null,null,null);
                if(cursor2 != null)
                    cursor2.moveToLast();
                String loginid = cursor2.getString(cursor2.getColumnIndex(Entries.COLUMN_NAME_ID_P));
                Log.d("LoginID",loginid);
                cursor2.close();
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTAPPJOINID,loginid);
                values.put(Entries.COLUMN_NAME_DOCID, message);
                values.put(Entries.COLUMN_NAME_SETDATE,dd );
                values.put(Entries.COLUMN_NAME_TIME, book);
                wdb.insert(Entries.TABLE_NAME_BOOKING, null, values);
            }
        });


        String sq = "SELECT  * FROM " + Entries.TABLE_NAME_BOOKING;
        Cursor cur = rdb.rawQuery(sq, null);
        if (cur.moveToFirst())
        {
            do
            {   String checkDate= cur.getString(cur.getColumnIndex("SETDATE"));
                String checkTime = cur.getString(cur.getColumnIndex("TIME"));


                if(checkDate.equals(dd))
                {
                    if(checkTime.equals("9:00am to 10:00am"))
                    { btn1.setEnabled(false);}
                    if(checkTime.equals("10:00am to 11:00am"))
                    { btn2.setEnabled(false);}
                    if(checkTime.equals("11:00am to 12:00pm"))
                    { btn3.setEnabled(false);}
                    if(checkTime.equals("1:00pm to 2:00pm"))
                    { btn4.setEnabled(false);}
                    if(checkTime.equals(" 2:00pm to 3:00pm"))
                    { btn5.setEnabled(false);}
                    if(checkTime.equals("3:00pm to 4:00pm"))
                    { btn6.setEnabled(false);}
                    if(checkTime.equals("4:00pm to 5:00pm"))
                    { btn7.setEnabled(false);}
                    if(checkTime.equals("5:00pm to 6:00pm"))
                    { btn8.setEnabled(false);}
                }
            }while (cur.moveToNext());
        }



    }

    public void showMessage(String title, String Message) {
        AlertDialog builder = new AlertDialog.Builder(this).create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Bookings.this,"BOOKING SUCCESSFUL",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setButton(Dialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}
