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

    //tabla que tiene lo de metas
    private static final String TABLE_METAS = "metas";
    private static final String ID_META = "id_meta";
    private static final String NOM_META= "nombre_meta";
    private static final String POS = "posicion";
    private static final String DESC = "descripcion";
    private static final String VALOR = "valor";
    private static final String LOGRADO = "logrado";

    //tabla que tiene lo de suscripciones
    private static final String TABLE_SUSC = "metas";
    private static final String NOM_SUSC= "nombre_sub";
    private static final String MONTO3 = "monto";
    private static final String PER = "periodicidad";
    private static final String ESTADO = "estado";
    private static final String TIPO = "tipo";
    private static final String FALTA = "falta";


    /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT.......);*/

    private static final String CREATE_TABLE_MOV = "CREATE TABLE "
            + TABLE_MOV + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + MOV + " TEXT ," + NOM + " TEXT ," + FECHA + " DATETIME DEFAULT CURRENT_TIMESTAMP ," + MONTO + " INTEGER ," + CAT + " TEXT ," + BOL + " TEXT ," + CANT + " INTEGER );";

    private static final String CREATE_TABLE_BOLSILLOS = "CREATE TABLE "
            + TABLE_BOL+ "(" + BOLSILLOS
            + " TEXT PRIMARY KEY," + MONTO2+ " TEXT );";

    private static final String CREATE_TABLE_METAS = "CREATE TABLE "
            + TABLE_METAS + "(" + ID_META
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + NOM_META + " TEXT ," + POS + " INTEGER ," + LOGRADO + " DATETIME ," + DESC + " TEXT ," + VALOR + " TEXT );";

    private static final String CREATE_TABLE_SUSC = "CREATE TABLE "
            + TABLE_SUSC + "(" + NOM_SUSC
            + " TEXT PRIMARY KEY ," + MONTO3 + " INTEGER ," + PER + " INTEGER ," + ESTADO + " TEXT ," + TIPO + " TEXT ," + FALTA + " INTEGER);";

    public DatabaseHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_MOV);
        Log.d("table", CREATE_TABLE_BOLSILLOS);
        Log.d("table", CREATE_TABLE_METAS);
        Log.d("table", CREATE_TABLE_SUSC);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOV);
        db.execSQL(CREATE_TABLE_BOLSILLOS);
        db.execSQL(CREATE_TABLE_METAS);
        db.execSQL(CREATE_TABLE_SUSC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_MOV + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_BOL + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_METAS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_SUSC + "'");
        onCreate(db);
    }

    public long addMov(String mov2, String nom2, int monto2, String cat2, String bol2, int cant2) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(MOV, mov2);
        values.put(NOM, nom2);
        values.put(MONTO, monto2);
        values.put(CAT, cat2);
        values.put(BOL, bol2);
        values.put(CANT, cant2);
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
    /*public ArrayList<String> getAllStudentsList() {
        ArrayList<String> movthiArrayList = new ArrayList<String>();
        String name="";
        String selectQuery = "SELECT  * FROM " + TABLE_MOV;
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