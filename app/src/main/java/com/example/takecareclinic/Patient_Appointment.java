package com.example.takecareclinic;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class Patient_Appointment extends AppCompatActivity {
    MyDatabase db;

    int mYear,mMonth,mDay;
    private ActionBar ab;
    EditText message_text;
    public final static String MESSAGE_KEY = "message_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__appointment);

        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#87cefa")));
        setTitle("PATIENT APPOINTMENT");

        db = new MyDatabase(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();
        final TableLayout theView = (TableLayout) findViewById(R.id.tableLayout);



        final Button btn1 = (Button)findViewById(R.id.btnBooking);
        final Button date = (Button) findViewById(R.id.btnDate);
        final Button view = (Button)findViewById(R.id.btnView);
        final EditText docid = (EditText) findViewById(R.id.txtDocId);
        final Spinner spn = (Spinner)findViewById(R.id.spnCategory);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message_text = (EditText) findViewById(R.id.txtDocId);
                String message = message_text.getText().toString();
                Intent intent = new Intent(Patient_Appointment.this, Bookings.class);
                intent.putExtra(MESSAGE_KEY, message);
                startActivity(intent);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Calendar mcurrentDate = Calendar.getInstance();
                 mYear = mcurrentDate.get(Calendar.YEAR);
                 mMonth = mcurrentDate.get(Calendar.MONTH);
                 mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                 DatePickerDialog mDatePicker = new DatePickerDialog(Patient_Appointment.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "dd/MM/yy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                        date.setText(sdf.format(myCalendar.getTime()));
                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String spec = spn.getSelectedItem().toString();
                final String dt = date.getText().toString();

                theView.removeAllViews();

                try {
                    Cursor cursor = rdb.rawQuery("select d.DOCTORJOINID,d.NAME from doctorform d, doctorSchedule ds " +
                            " where d.ID1 = ds.DOCTORFORMID and d.SPECIALITY = ? and ds.DATE = ? ", new String[] {spec,dt});;
                    if (cursor != null) {
                        cursor.moveToFirst();
                        TextView data;
                        TableRow row;
                        int cnt = 0 ;
                        do {
                            row = new TableRow(Patient_Appointment.this);
                            row.setPadding(2,2,2,2);
                            if (cnt++ % 2 == 0)
                                row.setBackgroundColor(Color.WHITE);
                            for (int x = 0; x < cursor.getColumnCount(); x++) {
                                data = new TextView(Patient_Appointment.this);
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
    }
}
