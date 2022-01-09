package com.example.takecareclinic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TakeCareClinicDatabase.db";


    //DOCTOR TABLE
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Entries.TABLE_NAMED + " (" +
                    Entries.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_UN + " TEXT," +
                    Entries.COLUMN_NAME_PW+ " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Entries.TABLE_NAMED;



    //PATIENT PAGE
    private static final String SQL_CREATE_ENTRIES_P =
            "CREATE TABLE " + Entries.TABLE_NAMEP + " (" +
                    Entries.COLUMN_NAME_ID_P + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_UN_P + " TEXT," +
                    Entries.COLUMN_NAME_PW_P+ " TEXT)";
    private static final String SQL_DELETE_ENTRIES_P =
            "DROP TABLE IF EXISTS " + Entries.TABLE_NAMEP;




    //DOCTOR FORM
    private static final String SQL_CREATE_ENTRIES_DOCTOR_FORM =
            "CREATE TABLE " + Entries.TABLE_NAME1 + " (" +
                    Entries.COLUMN_NAME_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_DOCTORJOINID + " TEXT,"+
                    Entries.COLUMN_NAME_NAME + " TEXT," +
                    Entries.COLUMN_NAME_AGE + " TEXT," +
                    Entries.COLUMN_NAME_CONTACT + " TEXT," +
                    Entries.COLUMN_NAME_ADDRESS + " TEXT," +
                    Entries.COLUMN_NAME_SPECIALITY + " TEXT)";
    private static final String SQL_DELETE_ENTRIES_DOCTOR_FORM =
            "DROP TABLE IF EXISTS " + Entries.TABLE_NAME1;


    //PATIENT FORM
    private static final String SQL_CREATE_ENTRIES_PATIENT_FORM =
            "CREATE TABLE " + Entries.TABLE_NAME2 + " (" +
                    Entries.COLUMN_NAME_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_PATIENTJOINID + " TEXT,"+
                    Entries.COLUMN_NAME_NAMEP + " TEXT," +
                    Entries.COLUMN_NAME_AGEP + " TEXT," +
                    Entries.COLUMN_NAME_SEX+ " TEXT," +
                    Entries.COLUMN_NAME_BLOODGROUP+ " TEXT," +
                    Entries.COLUMN_NAME_ADDRESSS + " TEXT," +
                    Entries.COLUMN_NAME_CONNO + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_PATIENT_FORM =
            "DROP TABLE IF EXISTS " + Entries.TABLE_NAME2;

    //PATIENT BOOK APOINTMENTS
    private static final String SQL_CREATE_ENTRIES_BOOKING =
            "CREATE TABLE " + Entries.TABLE_NAME_BOOKING + " (" +
                    Entries.COLUMN_NAME_BOOKINGID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_PATIENTAPPJOINID+ " TEXT,"+
                    Entries.COLUMN_NAME_DOCID + " TEXT," +
                    Entries.COLUMN_NAME_SETDATE+ " TEXT," +
                    Entries.COLUMN_NAME_TIME + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_BOOKING =
            "DROP TABLE IF EXISTS " + Entries.TABLE_NAME_BOOKING;


    //DOCTOR SCHEDULE
    private static final String SQL_CREATE_ENTRIES_DS =
            "CREATE TABLE " + Entries.TABLE_NAMEDS + " (" +
                    Entries.COLUMN_NAME_IDDS + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_DOCTORFORMID + " TEXT,"+
                    Entries.COLUMN_NAME_DATE + " TEXT," +
                    Entries.COLUMN_NAME_TIME1 + " TEXT," +
                    Entries.COLUMN_NAME_TIME2 + " TEXT," +
                    Entries.COLUMN_NAME_TIME3 + " TEXT," +
                    Entries.COLUMN_NAME_TIME4 + " TEXT," +
                    Entries.COLUMN_NAME_TIME5 + " TEXT," +
                    Entries.COLUMN_NAME_TIME6 + " TEXT," +
                    Entries.COLUMN_NAME_TIME7 + " TEXT," +
                    Entries.COLUMN_NAME_TIME8+ " TEXT)";
    private static final String SQL_DELETE_ENTRIES_DS =
            "DROP TABLE IF EXISTS " + Entries.TABLE_NAMEDS;


    private static final String SQL_CREATE_ENTRIES_QUESTION =
            "CREATE TABLE " + Entries.TABLE_NAME_QUESTION + " (" +
                    Entries.COLUMN_NAME_QUESTIONID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_PATIENTID+ " TEXT,"+
                    Entries.COLUMN_NAME_DOCTORID + " TEXT," +
                    Entries.COLUMN_NAME_QUESTION + " TEXT," +
                    Entries.COLUMN_NAME_STATUS + " TEXT)";
    private static final String SQL_DELETE_ENTRIES_QUESTION =
            "DROP TABLE IF EXISTS " + Entries.TABLE_NAME_QUESTION;

    private static final String SQL_CREATE_ENTRIES_ANSWER =
            "CREATE TABLE " + Entries.TABLE_NAME_ANSWER + " (" +
                    Entries.COLUMN_NAME_ANSWERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entries.COLUMN_NAME_QUESTIONJOINID+ " TEXT," +
                    Entries.COLUMN_NAME_A_PATIENTID+ " TEXT,"+
                    Entries.COLUMN_NAME_A_DOCTORID + " TEXT," +
                    Entries.COLUMN_NAME_ANSWER + " TEXT," +
                    Entries.COLUMN_NAME_A_STATUS + " TEXT)";
    private static final String SQL_DELETE_ENTRIES_ANSWER =
            "DROP TABLE IF EXISTS " + Entries.TABLE_NAME_ANSWER;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("MyDatabase","Constructor");
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_DS);
        db.execSQL(SQL_CREATE_ENTRIES_BOOKING);
        db.execSQL(SQL_CREATE_ENTRIES_P);
        db.execSQL(SQL_CREATE_ENTRIES_DOCTOR_FORM);
        db.execSQL(SQL_CREATE_ENTRIES_PATIENT_FORM);
        db.execSQL(SQL_CREATE_ENTRIES_ANSWER);
        db.execSQL(SQL_CREATE_ENTRIES_QUESTION);
        Log.d("MyDatabase","onCreate");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_DS);
        db.execSQL(SQL_DELETE_ENTRIES_BOOKING);
        db.execSQL(SQL_DELETE_ENTRIES_P);
        db.execSQL(SQL_DELETE_ENTRIES_DOCTOR_FORM);
        db.execSQL(SQL_DELETE_ENTRIES_PATIENT_FORM);
        Log.d("MyDB","OnUpgrade");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyDatabase","onDowngrade");
        onUpgrade(db, oldVersion, newVersion);
    }


    public Cursor getAllDataP() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Entries.TABLE_NAMED, null);
        return res;
    }
    public Cursor getAllPatient() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Entries.TABLE_NAMEP, null);
        return res;
    }
    public Cursor getDocForm() {
        SQLiteDatabase dbb = this.getWritableDatabase();
        Cursor ress = dbb.rawQuery("select * from " + Entries.TABLE_NAME1, null);
        return ress;
    }
    public Cursor getAllDS() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Entries.TABLE_NAMEDS, null);
        return res;
    }
    public Cursor getPatientForm() {
        SQLiteDatabase dbb = this.getWritableDatabase();
        Cursor ress = dbb.rawQuery("select * from " + Entries.TABLE_NAME2, null);
        return ress;
    }




    public boolean deleteRec(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(Entries.TABLE_NAMED, "Id=?",
                new String[]{Integer.toString(id)});
        if (d > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cancelApp(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(Entries.TABLE_NAME_BOOKING, "BOOKID=?",
                new String[]{Integer.toString(id)});
        if (d > 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean deletePatient(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(Entries.TABLE_NAMEP, "IDP=?",
                new String[]{Integer.toString(id)});
        if (d > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean loginDoctor(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] columns = {Entries.COLUMN_NAME_ID};
        String sel = Entries.COLUMN_NAME_UN + "=?" + " and " + Entries.COLUMN_NAME_PW+ "=?";
        String[] selargs = {username, password};
        Cursor c = sqLiteDatabase.query(Entries.TABLE_NAMED, columns, sel, selargs, null, null, null);
        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean loginPatient(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] columns = {Entries.COLUMN_NAME_ID_P};
        String sel = Entries.COLUMN_NAME_UN_P + "=?" + " and " + Entries.COLUMN_NAME_PW_P+ "=?";
        String[] selargs = {username, password};
        Cursor c = sqLiteDatabase.query(Entries.TABLE_NAMEP, columns, sel, selargs, null, null, null);
        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }



}
