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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PatientCancelAppointment extends AppCompatActivity {
    private MyDatabase db;
    private ActionBar ab;
    public final static String MESSAGE_KEY2 ="message_key2";
    String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_cancel_appointment);
        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();
        ab=getSupportActionBar();

        Intent intent = getIntent();
        final String message2 = intent.getStringExtra(MESSAGE_KEY2);
        m=message2;

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("CANCEL APPOINTMENT");
        final TableLayout theView = (TableLayout) findViewById(R.id.thePatientTable);

        final EditText id = findViewById(R.id.enter);

        theView.removeAllViews();

        String a=Entries.TABLE_NAME1;
        String b=Entries.TABLE_NAME_BOOKING;
        String c=Entries.COLUMN_NAME_DOCTORJOINID;
        String d=Entries.COLUMN_NAME_DOCID;
        String e=Entries.COLUMN_NAME_PATIENTAPPJOINID;

        String selectQuery = "SELECT  BOOKID, NAME,SETDATE,TIME FROM " + Entries.TABLE_NAME1 +" INNER JOIN " + Entries.TABLE_NAME_BOOKING + " ON " + a + "." + c + " = " + b + "." + d
                + " WHERE "  + e + " = " + m   ;

        try {
            Cursor cursor = rdb.rawQuery(selectQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
                TextView data;
                TableRow row;
                int cnt = 0 ;
                do {
                    row = new TableRow(PatientCancelAppointment.this);
                    row.setPadding(2,2,2,2);
                    if (cnt++ % 2 == 0)
                        row.setBackgroundColor(Color.WHITE);
                    for (int x = 0; x < cursor.getColumnCount(); x++) {
                        data = new TextView(PatientCancelAppointment.this);
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
            }
        } catch (Exception ex) { }


        //DELETE
      Button dd = (Button) findViewById(R.id.cancel);
        dd.setOnClickListener(new View.OnClickListener() {
            boolean isDeleted;

            @Override
            public void onClick(View v) {
                isDeleted = db.cancelApp(Integer.parseInt(id.getText().toString()));
                if (isDeleted) {
                    Toast.makeText(PatientCancelAppointment.this,
                            "Appointment cancelled successfully",
                            Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(PatientCancelAppointment.this,
                            "Appointment not canceled",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        //GO BACK
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientCancelAppointment.this, PatientPageNav.class));
            }
        });


    }


}
