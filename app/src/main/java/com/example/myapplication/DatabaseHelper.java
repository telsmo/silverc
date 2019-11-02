package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


// El nombre de la base de datos se asigna en la variable "name" que llega del main
public class DatabaseHelper extends SQLiteOpenHelper {

    private  final int DATABASE_VERSION = 1;

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
    private static final String DESCRIPCION= "nombre_descripcion";
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

    //Tabla de categorias
    private static final String Table_CATE= "cat";
    private static  final String NOM_CATE="nombre_cate";

    /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT.......);*/

    private static final String CREATE_TABLE_MOV = "CREATE TABLE "
            + TABLE_MOV + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + MOV + " TEXT ," + NOM + " TEXT ," + FECHA + " DATETIME DEFAULT CURRENT_TIMESTAMP ," + MONTO + " INTEGER ," + CAT + " TEXT ," + BOL + " TEXT ," + CANT + " INTEGER );";

    private static final String CREATE_TABLE_BOLSILLOS = "CREATE TABLE "
            + TABLE_BOL+ "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + BOLSILLOS
            + " TEXT ," + DESCRIPCION
            + " TEXT ,"+ MONTO2+ " INTEGER );";

    private static final String CREATE_TABLE_METAS = "CREATE TABLE "
            + TABLE_METAS + "(" + ID_META
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + NOM_META + " TEXT ," + POS + " INTEGER ," + LOGRADO + " DATETIME ," + DESC + " TEXT ," + VALOR + " TEXT );";

    private static final String CREATE_TABLE_SUSC = "CREATE TABLE "
            + TABLE_SUSC + "(" + NOM_SUSC
            + " TEXT PRIMARY KEY ," + MONTO3 + " INTEGER ," + PER + " INTEGER ," + ESTADO + " TEXT ," + TIPO + " TEXT ," + FALTA + " INTEGER);";
    private static final String CREATE_TABLE_CATE = "CREATE TABLE "
            + Table_CATE + "(" + NOM_CATE
            + " TEXT PRIMARY KEY );";

    public DatabaseHelper(Context context, String name) {
        super(context, name, null, 1);

        Log.d("table", CREATE_TABLE_MOV);
        Log.d("table", CREATE_TABLE_BOLSILLOS);
        Log.d("table", CREATE_TABLE_METAS);
        Log.d("table", CREATE_TABLE_SUSC);
        Log.d("table", CREATE_TABLE_CATE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOV);
        db.execSQL(CREATE_TABLE_BOLSILLOS);
        db.execSQL(CREATE_TABLE_METAS);
       // db.execSQL(CREATE_TABLE_SUSC);
        db.execSQL(CREATE_TABLE_CATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_MOV + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_BOL + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_METAS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_SUSC + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + Table_CATE + "'");
        onCreate(db);
    }
    public Long addCate(String ca){
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(NOM_CATE, ca);
        // insert row in students table
        long insert = db.insert(Table_CATE, null, values);

        return insert;
    }
    public List<String> loadCate(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM "+ Table_CATE,null);
        List<String> array = new ArrayList<String>();
        while(datos.moveToNext()){
            String uname = datos.getString(datos.getColumnIndex(NOM_CATE));
            array.add(uname);
        }
        return array;
    }
    public String addMov(String mov2, String nom2, int monto2, String cat2, String bol2, int cant2) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(MOV, mov2);
        values.put(NOM, nom2);
        values.put(MONTO, monto2);
        values.put(CAT, cat2);
        values.put(BOL, bol2);
        values.put(CANT, cant2);
        long insert = db.insert(TABLE_MOV, null, values);

        String[] bolsillo2= new String[]{bol2};
        Cursor datos2 = db.rawQuery("SELECT * FROM "+ TABLE_BOL +" WHERE "+ BOLSILLOS +" = ?",bolsillo2);
        String lol = DatabaseUtils.dumpCursorToString(datos2);
        datos2.moveToFirst();
        String lol2= String.valueOf(datos2.getInt(datos2.getColumnIndex(MONTO2)));
        Integer cant = monto2*cant2;
        Integer mont = Integer.parseInt(lol2);
        if (mov2.equals("Compra")) {
            mont= mont-cant;
        }else{if (mov2.equals("Ingreso")){
            mont=mont+cant;
        }}
        //ContentValues cv = new ContentValues();
        //cv.put(MONTO2,mont);
        String montovich= String.valueOf(mont);

        Cursor bruh= db.rawQuery("UPDATE "+TABLE_BOL+" SET "+MONTO2+" ="+montovich+" WHERE "+BOLSILLOS+" =?",bolsillo2);
        bruh.moveToFirst();
        bruh.close();
        //db.update(TABLE_BOL,cv,BOLSILLOS+"="+bol2,null);
        return lol;
    }

    public long addBol(String nom,String desc, int cant) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(BOLSILLOS, nom);
        values.put(DESCRIPCION,desc);
        cant=cant-cant;
        values.put(MONTO2, cant);
        // insert row in students table
        long insert = db.insert(TABLE_BOL, null, values);

        return insert;
    }
    public Cursor getTableMov(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM "+ TABLE_MOV+" ORDER BY "+KEY_ID+" DESC",null);

        return datos;
    }
    public Cursor getTableMovA(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT monto,categoria,cantidad FROM "+ TABLE_MOV,null);

        return datos;
    }
    public Cursor getTableBol(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos2 = db.rawQuery("SELECT * FROM "+ TABLE_BOL,null);
        return datos2;
    }
    public Integer getSaldoTotal(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM "+ TABLE_BOL,null);
        Integer montoi;
        Integer total=0;
                while (datos.moveToNext()){
                montoi=datos.getInt(datos.getColumnIndex(MONTO2));
                total= montoi+total;
        }
        return total;
    }
    public Cursor getBuscador(String values, String column){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos= db.rawQuery("SELECT * FROM "+TABLE_MOV+" WHERE "+column+" like ?", new String[] {"%"+values+"%"});
        return datos;
    }
    public void updateBol(String bol, Integer cant){

        SQLiteDatabase db = this.getWritableDatabase();
        //String selectQuery= "SELECT * FROM "+ TABLE_BOL +" WHERE "+ BOLSILLOS +" = ?";
        //String[] bolsillo= new String[]{bol};
        //Cursor c =db.rawQuery(selectQuery,bolsillo);
        Cursor datos2 = db.rawQuery("SELECT * FROM "+ TABLE_BOL,null);
        //if(datos2!=null && datos2.getCount() > 0) {
          //  if (datos2.moveToFirst()) {
        //while (c.moveToNext()){
                Integer mont = datos2.getInt(datos2.getColumnIndex(MONTO2));
                mont= mont-cant;
                ContentValues cv = new ContentValues();
                cv.put(MONTO2,Integer.toString(mont));
                //SQLiteDatabase db2= this.getWritableDatabase();
                //Cursor datos2= db2.rawQuery("UPDATE bolsillos SET monto=? WHERE nombre_bolsillo =?",new String[]{montovich,"'"+bol+"'"});
                //db.update(TABLE_BOL,cv,BOLSILLOS+"="+bol,null);
            /*ContentValues cv = new ContentValues();
            cv.put(MONTO2,Integer.toString(mont));
            db.update(TABLE_BOL,cv,BOLSILLOS+"="+bol,null);
        }*/
           // }
        //}
        //String mont =datos2.getString(3);


        //String montovich = Integer.toString(mont);


    }
    public ArrayList<String> getbolsillos(){
        ArrayList<String> bolsillosArray =new ArrayList<String>();
        String name;
        String selectQuery= "SELECT * FROM "+ TABLE_BOL;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor c =db.rawQuery(selectQuery,null);
        while (c.moveToNext()){
                name=c.getString(c.getColumnIndex(BOLSILLOS));
                bolsillosArray.add(name);
            }
        return bolsillosArray;

    }
    public void updateMov(String id, String dato, String caso){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String[] referencia = {
                MOV,
                NOM,
                MONTO,
                CANT,
                "Drug Addiction",
                FECHA,
                BOL,
                CAT
        };
        cv.put(referencia[Integer.parseInt(caso)],dato);
        db.update(TABLE_MOV, cv, "id_mov="+id, null);
    }
    public void deleteMov(String id, String dato, String caso){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String[] referencia = {
                MOV,
                NOM,
                MONTO,
                CANT,
                "Drug Addiction",
                FECHA,
                BOL,
                CAT
        };
        db.execSQL("DELETE FROM "+TABLE_MOV+" WHERE "+KEY_ID+"="+id);
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
        ArrayList<String> movArrayList = new ArrayList<String>();
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