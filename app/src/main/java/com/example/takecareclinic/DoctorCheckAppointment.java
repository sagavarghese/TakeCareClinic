package com.example.takecareclinic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DoctorCheckAppointment extends AppCompatActivity {
    private MyDatabase db;
    private ActionBar ab;
    public final static String MESSAGE_KEY2 ="message_key2";
    String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_check_appointment);

        Intent intent = getIntent();
        final String message2 = intent.getStringExtra(MESSAGE_KEY2);
        TextView textView = (TextView)findViewById(R.id.match);
        textView.setText(message2);
        m=message2;

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("DOCTOR CHECK APPOINTMENTS");
        db = new MyDatabase(this);
        final TableLayout theView = (TableLayout) findViewById(R.id.theTableChk);

        String a=Entries.TABLE_NAME2;
        String b=Entries.TABLE_NAME_BOOKING;
        String c=Entries.COLUMN_NAME_PATIENTJOINID;
        String d=Entries.COLUMN_NAME_PATIENTAPPJOINID;
        String e=Entries.COLUMN_NAME_DOCID;



        String selectQuery = "SELECT  NAMEP,SEXP,SETDATE,TIME FROM " + Entries.TABLE_NAME2 +" INNER JOIN " + Entries.TABLE_NAME_BOOKING + " ON " + a + "." + c + " = " + b + "." + d
                + " WHERE "  + e + " = " + m ;
        try {
            Cursor cursor = rdb.rawQuery(selectQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
                TextView data;
                TableRow row;
                int cnt = 0 ;
                do {
                    row = new TableRow(DoctorCheckAppointment.this);
                    row.setPadding(2,2,2,2);
                    if (cnt++ % 2 == 0)
                        row.setBackgroundColor(Color.WHITE);
                    for (int x = 0; x < cursor.getColumnCount(); x++) {
                        data = new TextView(DoctorCheckAppointment.this);
                        if (x == 0) {
                            data.setTypeface(Typeface.DEFAULT_BOLD);
                            data.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        data.setText(cursor.getString(x));
                        row.addView(data);
                    }
                    theView.addView(row);
                } while (cursor.moveToNext());
                theView.setShrinkAllColumns(true);
                theView.setStretchAllColumns(true);
                cursor.close();
            }
        } catch (Exception ex) { }
    }
}
