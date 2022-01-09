package com.example.takecareclinic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
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

public class Patient_Questions extends AppCompatActivity {
    MyDatabase db;
    String dtid,qid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__questions);

        db = new MyDatabase(this);
        final SQLiteDatabase rdb = db.getReadableDatabase();

        final Button bt = (Button) findViewById(R.id.btnQnList);
        final Button sub = (Button) findViewById(R.id.btnSubmitAnswer);
        final TableLayout theView = (TableLayout) findViewById(R.id.tblQnList);
        final EditText paId = (EditText)findViewById(R.id.txtPatId);
        final EditText ans = (EditText) findViewById(R.id.txtAnswer);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theView.removeAllViews();
                //String selectQuery = "SELECT  * FROM " + Entries.TABLE_NAME_QUESTION;
                try {
                    Cursor cursor = rdb.rawQuery("select PATIENTID,QUESTION from QUESTION where STATUS = ?",new String[] {"0"});
                    if (cursor != null) {
                        cursor.moveToFirst();
                        TextView data;
                        TableRow row;
                        int cnt = 0 ;
                        do {
                            row = new TableRow(Patient_Questions.this);
                            row.setPadding(2,2,2,2);
                            if (cnt++ % 2 == 0)
                                row.setBackgroundColor(Color.WHITE);
                            for (int x = 0; x < cursor.getColumnCount(); x++) {
                                data = new TextView(Patient_Questions.this);
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
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {

            SQLiteDatabase wdb = db.getWritableDatabase();
            SQLiteDatabase rdb = db.getReadableDatabase();


            @Override
            public void onClick(View v) {

                final String pati = paId.getText().toString();
                final String answ = ans.getText().toString();

                Cursor cursor = rdb.rawQuery("select DOCTORID,QUESTIONID from question where PATIENTID = ?",new String[] {pati});
                if(cursor.moveToFirst())
                {
                    dtid = cursor.getString(0);
                    qid = cursor.getString(1);
                }
                cursor.close();

                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_QUESTIONJOINID,qid);
                values.put(Entries.COLUMN_NAME_A_DOCTORID, dtid);
                values.put(Entries.COLUMN_NAME_A_PATIENTID, pati);
                values.put(Entries.COLUMN_NAME_ANSWER, answ);
                values.put(Entries.COLUMN_NAME_A_STATUS,"0");

                long check = wdb.insert(Entries.TABLE_NAME_ANSWER,null,values);
                if(check>0)
                {
                    Toast.makeText(Patient_Questions.this,"ANSWER SENT SUCCESFULLY",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Patient_Questions.this, "ANSWER NOT SENT",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
