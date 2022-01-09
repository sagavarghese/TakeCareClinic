package com.example.takecareclinic;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ask_Doctor extends AppCompatActivity {

    MyDatabase db;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask__doctor);
        db = new MyDatabase(this);
        final SQLiteDatabase rdb = db.getReadableDatabase();

        Intent i = getIntent();
        final String user = i.getStringExtra("un");
        final String didi = i.getStringExtra("iid");
     //   Log.d("iiiiiiiiiiiiiiiid",didi);
        final EditText doctoid = (EditText) findViewById(R.id.txtDocID);
        final EditText qn = (EditText) findViewById(R.id.txtQuestion);
        final Button btn = (Button) findViewById(R.id.btnSubmit);
        final Spinner cat = (Spinner) findViewById(R.id.spinCategory);
        final Button bt = (Button) findViewById(R.id.btnView);
        final TableLayout theView = (TableLayout) findViewById(R.id.table);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Ask_Doctor.this,doctid,Toast.LENGTH_SHORT).show();
                //Toast.makeText(Ask_Doctor.this,qst,Toast.LENGTH_SHORT).show();
                String spec = cat.getSelectedItem().toString();
                theView.removeAllViews();
                String selectQuery = "SELECT  * FROM " + Entries.TABLE_NAMED;
                try {
                    Cursor cursor = rdb.rawQuery("select DOCTORJOINID,NAME from doctorform where SPECIALITY = ? ", new String[]{spec});
                    if (cursor != null) {
                        cursor.moveToFirst();
                        TextView data;
                        TableRow row;
                        int cnt = 0 ;
                        do {
                            row = new TableRow(Ask_Doctor.this);
                            row.setPadding(2,2,2,2);
                            if (cnt++ % 2 == 0)
                                row.setBackgroundColor(Color.WHITE);
                            for (int x = 0; x < cursor.getColumnCount(); x++) {
                                data = new TextView(Ask_Doctor.this);
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
        });
        btn.setOnClickListener(new View.OnClickListener() {
            SQLiteDatabase wdb = db.getWritableDatabase();

            @Override
            public void onClick(View v) {

                final String doctid = doctoid.getText().toString();
                final String qst = qn.getText().toString();
                try {

                    ContentValues values = new ContentValues();
                    values.put(Entries.COLUMN_NAME_PATIENTID, didi);
                    values.put(Entries.COLUMN_NAME_DOCTORID, doctid);
                    values.put(Entries.COLUMN_NAME_QUESTION, qst);
                    values.put(Entries.COLUMN_NAME_STATUS, "0");

                    long check = wdb.insert(Entries.TABLE_NAME_QUESTION, null, values);
                    if (check > 0) {
                        Toast.makeText(Ask_Doctor.this, didi, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Ask_Doctor.this, "QUESTION NOT SEND", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){}
            }
        });
    }
}
