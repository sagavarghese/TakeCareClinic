package com.example.takecareclinic;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAnswer extends AppCompatActivity {
    MyDatabase db;
    String qn,an,qnid;
    String patientid,doctorid,question,qnjnid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answer);
        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();

        final Button ok = (Button) findViewById(R.id.btnOk);

        Intent i = getIntent();
        String ppid = i.getStringExtra("id");
        SQLiteDatabase rdb = db.getReadableDatabase();

        TextView yQn = (TextView) findViewById(R.id.yourQn);
        TextView yAn = (TextView) findViewById(R.id.yourAnswer);

        Cursor cursor = rdb.rawQuery("select QUESTION,QUESTIONID from question where PATIENTID = ?",new String[] {ppid});
        if(cursor.moveToFirst())
        {
            qn = cursor.getString(0);
            qnid = cursor.getString(1);
        }
        cursor.close();

        Cursor cursor1 = rdb.rawQuery("select ANSWER from answer where QUESTIONJOINID = ? ",new String[] {qnid});
        if(cursor1.moveToFirst())
        {
            an = cursor1.getString(0);
        }
        cursor1.close();
        yQn.setText(qn);
        yAn.setText(an);

        /*
         private static final String SQL_CREATE_ENTRIES_QUESTION =
            "CREATE TABLE " + Entries.TABLE_NAME_QUESTION + " (" +
                    Entries.COLUMN_NAME_QUESTIONID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_PATIENTID+ " TEXT,"+
                    Entries.COLUMN_NAME_DOCTORID + " TEXT," +
                    Entries.COLUMN_NAME_QUESTION + " TEXT," +
                    Entries.COLUMN_NAME_STATUS + " TEXT)";
         */
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor;
                SQLiteDatabase rdb = db.getReadableDatabase();
                try {
                    cursor = rdb.rawQuery("select q.PATIENTID,q.DOCTORID,q.QUESTION,a.QUESTIONJOINID from question q, answer a where q.QUESTIONID =? ", new String[]{"a." + Entries.COLUMN_NAME_QUESTIONJOINID});
                    if(cursor.moveToFirst()) {
                        patientid = cursor.getString(0);
                        doctorid = cursor.getString(1);
                        question = cursor.getString(2);
                        qnjnid = cursor.getString(3);
                    }
                    cursor.close();
                }catch(Exception e)
                {}
                ContentValues values = new ContentValues();
                values.put(Entries.COLUMN_NAME_PATIENTID,patientid);
                values.put(Entries.COLUMN_NAME_DOCTORID, doctorid);
                values.put(Entries.COLUMN_NAME_QUESTION, question);
                values.put(Entries.COLUMN_NAME_STATUS, "1");

                long a = wdb.update(Entries.TABLE_NAME_QUESTION,values,null,null);
                if(a > 0)
                {
                    Toast.makeText(ViewAnswer.this,"Accepted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ViewAnswer.this,"Not Accepted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
