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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteDoctor extends AppCompatActivity {
    private MyDatabase db;
    private ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_doctor);

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();
        ab=getSupportActionBar();

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("DELETE DOCTOR");
        final TableLayout theView = (TableLayout) findViewById(R.id.theTable);

        final EditText id = findViewById(R.id.enter);

                theView.removeAllViews();
                String selectQuery = "SELECT  * FROM " + Entries.TABLE_NAMED;
                try {
                    Cursor cursor = rdb.rawQuery(selectQuery, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        TextView data;
                        TableRow row;
                        int cnt = 0 ;
                        do {
                            row = new TableRow(DeleteDoctor.this);
                            row.setPadding(2,2,2,2);
                            if (cnt++ % 2 == 0)
                                row.setBackgroundColor(Color.WHITE);
                            for (int x = 0; x < cursor.getColumnCount(); x++) {
                                data = new TextView(DeleteDoctor.this);
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
        Button dd = (Button) findViewById(R.id.del);
        dd.setOnClickListener(new View.OnClickListener() {
            boolean isDeleted;

            @Override
            public void onClick(View v) {
                isDeleted = db.deleteRec(Integer.parseInt(id.getText().toString()));
                if (isDeleted) {
                    Toast.makeText(DeleteDoctor.this,
                            "Record deleted successfully",
                            Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(DeleteDoctor.this,
                            "Record not deleted",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        //GO BACK
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteDoctor.this, AdminPageNav.class));
            }
        });


    }


}
