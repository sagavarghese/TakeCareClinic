package com.example.takecareclinic;

import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//CREATE VARIABLES
public class Entries implements BaseColumns {
    //DECLARE TABLE FOR DOCTOR LOGIN
    public static final String TABLE_NAMED = "doctorRegister";
    public static final String COLUMN_NAME_ID = "ID";
    public static final String COLUMN_NAME_UN = "UN";
    public static final String COLUMN_NAME_PW = "PW";;




    //TABLE FOR DOCTOR FORM
    public static final String TABLE_NAME1 = "doctorform";
    public static final String COLUMN_NAME_ID1 = "ID1";
    public static final String COLUMN_NAME_DOCTORJOINID ="DOCTORJOINID";
    public static final String COLUMN_NAME_NAME = "NAME";
    public static final String COLUMN_NAME_AGE = "AGE";
    public static final String COLUMN_NAME_CONTACT = "CONTACT";
    public static final String COLUMN_NAME_ADDRESS = "ADDRESS";
    public static final String COLUMN_NAME_SPECIALITY = "SPECIALITY";



    //TABLE FOR DOCTOR SCHEDULE
    public static final String TABLE_NAMEDS = "doctorSchedule";
    public static final String COLUMN_NAME_IDDS = "IDDS";
    public static final String COLUMN_NAME_DOCTORFORMID = "DOCTORFORMID";
    public static final String COLUMN_NAME_DATE = "DATE";
    public static final String COLUMN_NAME_TIME1 = "TIME1";
    public static final String COLUMN_NAME_TIME2 = "TIME2";
    public static final String COLUMN_NAME_TIME3 = "TIME3";
    public static final String COLUMN_NAME_TIME4 = "TIME4";
    public static final String COLUMN_NAME_TIME5 = "TIME5";
    public static final String COLUMN_NAME_TIME6 = "TIME6";
    public static final String COLUMN_NAME_TIME7 = "TIME7";
    public static final String COLUMN_NAME_TIME8 = "TIME8";




    //TABLE FOR PATIENT LOGIN
    public static final String TABLE_NAMEP = "patientRegister";
    public static final String COLUMN_NAME_ID_P = "IDP";
    public static final String COLUMN_NAME_UN_P = "UNP";
    public static final String COLUMN_NAME_PW_P = "PWP";;


    //TABLE FOR PATIENT FORM
    public static final String TABLE_NAME2 = "patientform";
    public static final String COLUMN_NAME_ID2 = "ID2";
    public static final String COLUMN_NAME_PATIENTJOINID ="PATIENTJOINID";
    public static final String COLUMN_NAME_NAMEP = "NAMEP";
    public static final String COLUMN_NAME_AGEP = "AGEP";
    public static final String COLUMN_NAME_SEX = "SEXP";
    public static final String COLUMN_NAME_BLOODGROUP = "BLOODGROUP";
    public static final String COLUMN_NAME_ADDRESSS = "ADDRESSS";
    public static final String COLUMN_NAME_CONNO = "CONNO";

    //TABLE FOR BOOKING APPOINMENT
    public static final String TABLE_NAME_BOOKING = "booking";
    public static final String COLUMN_NAME_BOOKINGID ="BOOKID";
    public static final String COLUMN_NAME_PATIENTAPPJOINID = "PATIENTAPPJOINID";
    public static final String COLUMN_NAME_DOCID = "DOCID";
    public static final String COLUMN_NAME_SETDATE ="SETDATE";
    public static final String COLUMN_NAME_TIME = "TIME";

    //TABLE FOR ASKING QUESTIONS TO DOCTOR
    public static final String TABLE_NAME_QUESTION = "question";
    public static final String COLUMN_NAME_QUESTIONID = "QUESTIONID";
    public static final String COLUMN_NAME_PATIENTID = "PATIENTID";
    public static final String COLUMN_NAME_DOCTORID = "DOCTORID";
    public static final String COLUMN_NAME_QUESTION = "QUESTION";
    public static final String COLUMN_NAME_STATUS = "STATUS";

    //TABLE FOR ANSWERS FROM DOCTOR
    public static final String TABLE_NAME_ANSWER = "answer";
    public static final String COLUMN_NAME_ANSWERID = "ANSWERID";
    public static final String COLUMN_NAME_QUESTIONJOINID = "QUESTIONJOINID";
    public static final String COLUMN_NAME_A_DOCTORID = "ADOCTORID";
    public static final String COLUMN_NAME_A_PATIENTID = "A_PATIENTID";
    public static final String COLUMN_NAME_ANSWER = "ANSWER";
    public static final String COLUMN_NAME_A_STATUS = "ASTATUS";

}

