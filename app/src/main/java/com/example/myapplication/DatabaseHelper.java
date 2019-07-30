package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


// El nombre de la base de datos se asigna en la variable "name" que llega del main
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    //tabla que tiene lo de gastos e ingresos
    private static final String TABLE_MOV = "movimientos";
    private static final String KEY_ID = "id_mov";
    private static final String MOV= "mov";
    private static final String NOM = "nombre_mov";
    private static final String FECHA = "fecha";
    private static final String MONTO = "monto";
    private static final String CAT = "categoria";
    private static final String BOL = "bolsillo";
    private static final String CANT = "cantidad";

    //Tabla de los bolsillos
    private static final String TABLE_BOL = "bolsillos";
    private static final String BOLSILLOS= "nombre_bolsillo";
    private static final String MONTO2 = "monto";


    /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_MOV + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + MOV + " TEXT ," + NOM + " TEXT ," + FECHA + " DATETIME DEFAULT CURRENT_TIMESTAMP ," + MONTO + " INTEGER ," + CAT + " TEXT ," + BOL + " TEXT ," + CANT + " INTEGER );";

    private static final String CREATE_TABLE_BOLSILLOS = "CREATE TABLE "
            + TABLE_BOL+ "(" + BOLSILLOS
            + " STRING PRIMARY KEY," + MONTO2+ " TEXT );";


    public DatabaseHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_USER);
        Log.d("table", CREATE_TABLE_BOLSILLOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BOLSILLOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_MOV + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_BOL + "'");
        onCreate(db);
    }

    public long addStudentDetail(String student) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(NOM, student);
        // insert row in students table
        long insert = db.insert(TABLE_MOV, null, values);

        return insert;
    }

    /*public ArrayList<String> getAllStudentsList() {
        ArrayList<String> studentsArrayList = new ArrayList<String>();
        String name="";
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                // adding to Students list
                studentsArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", studentsArrayList.toString());
        }
        return studentsArrayList;
    }*/
}