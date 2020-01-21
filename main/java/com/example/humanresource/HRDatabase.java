package com.example.humanresource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;




public class HRDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "hr.db";
    public static final String TABLE_NAME1 = "departments";
    public static final String TABLE_NAME2 = "employees";
    public static final String COL_10 = "_id";
    public static final String COL_11 = "department_name";
    public static final String COL_20 = "_id";
    public static final String COL_21 = "first_name";
    public static final String COL_22 = "last_name";
    public static final String COL_23 = "phone";
    public static final String COL_24 = "email";
    public String error = "";

    public static final int DATABASE_VERSION = 7;


    public HRDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String deptTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + " (" + COL_10 + " INTEGER " +
                    "PRIMARY KEY " +
                    "AUTOINCREMENT," + COL_11 + " VARCHAR NOT NULL);";
            String empTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + " (" + COL_20 + " INTEGER " +
                    "PRIMARY KEY AUTOINCREMENT," + COL_21 + " VARCHAR," + COL_22 + " VARCHAR," + COL_23 + " VARCHAR, dept_id INTEGER, FOREIGN KEY(dept_id) REFERENCES " + TABLE_NAME1 + "(" + COL_10 + "));";
            db.execSQL(deptTable);
            db.execSQL(empTable);
        }
        catch (Exception e)
        {
            error = e.toString();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }

    public long addDepartment(String dName)
    {
        long response = 0;
        try {
            SQLiteDatabase mydb = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COL_11, dName);
            response = mydb.insert(TABLE_NAME1, null, cv);
            mydb.close();


        }
        catch (Exception e)
        {
            error = e.toString();
        }
        return response;
    }

    public Cursor viewDepartment()
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        String vDept = "SELECT * FROM "+TABLE_NAME1;
        Cursor c = mydb.rawQuery(vDept,null);
        return c;
    }

}


