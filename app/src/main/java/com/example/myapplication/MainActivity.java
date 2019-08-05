package com.example.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
        private DatabaseHelper databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(sesion_iniciada()){
            setContentView(R.layout.mainlol);
            String name = nom_us() + contra();
            databaseHandler = new DatabaseHelper(this, name);
        }else{
            setContentView(R.layout.activity_main);
        }
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }



    public void gotomainlolinicial (View view){
//agarro lo de user y lo pongo en la variable name, para despues crear la base con ese nombre
        final EditText us = (EditText) findViewById(R.id.nom_us);
        final EditText ps = (EditText) findViewById(R.id.contra);

        String user = us.getText().toString();
        String pass = ps.getText().toString();
        String name= user+pass;
        //guarda en datos el user y la password
        registrar(user,pass);

        setContentView(R.layout.mainlol);
        databaseHandler = new DatabaseHelper(this, name);
    }

    //seleccionar para ir a cargar un gasto o ingreso.
    public void gotocargar (View view){

        setContentView(R.layout.cargar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        String[] categorias = new String[]{
                "Entretenimiento", "Comida", "Transporte", "Impuestos", "Otros"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categorias);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

    }
    public void cargar (View view){
        final EditText c1 = (EditText) findViewById(R.id.c1);
        final EditText c2 = (EditText) findViewById(R.id.c2);
        final Spinner c3 = (Spinner) findViewById(R.id.spinner1);
        final EditText c4 = (EditText) findViewById(R.id.c4);
        final EditText c5 = (EditText) findViewById(R.id.c5);
        final EditText c6 = (EditText) findViewById(R.id.c6);
        String c11 = c1.getText().toString();
        String c12 = c2.getText().toString();
        String c13 = c3.getSelectedItem().toString();
        String c14 = c4.getText().toString();
        String c15 = c5.getText().toString();
        String c16 = c6.getText().toString();

        long result= databaseHandler.addMov("egreso",c11,Integer.parseInt(c12),c13,c16,Integer.parseInt(c14));
        setContentView(R.layout.mainlol);

    }
    public void gotoingreso (View view){

        setContentView(R.layout.ingreso);

    }
    public void ingresar (View view){
        final EditText i1 = (EditText) findViewById(R.id.i1);
        final EditText i2 = (EditText) findViewById(R.id.i2);
        String c11 = i1.getText().toString();
        String c12 = i2.getText().toString();

        long result= databaseHandler.addMov("ingreso",c11,Integer.parseInt(c12),c11,c11,1);
        setContentView(R.layout.mainlol);

    }
    ///////////////////////////////////////////////////////////////////////////////
    public void gotomainlol (View view){

        setContentView(R.layout.mainlol);

    }
    public void gotobolsillo (View view){

        setContentView(R.layout.bolsillo);

    }
    public void gotocrearbolsillo (View view){

        setContentView(R.layout.crearbolsillo);

    }

    public boolean sesion_iniciada() {
        boolean a;
        SharedPreferences prefs = getSharedPreferences("mp", MODE_PRIVATE);
        String user = prefs.getString("user", "empty");//"empty" is the default value.
        if (user!="empty"){
            a=true;
        }else{
            a=false;
        }
        return a;
    }
    public String nom_us(){
        SharedPreferences prefs = getSharedPreferences("mp", MODE_PRIVATE);
        String nom = prefs.getString("user", "empty");//"No name defined" is the default value.
        return nom;
        }
    public String contra(){
        SharedPreferences prefs = getSharedPreferences("mp", MODE_PRIVATE);
        String pass = prefs.getString("pass", "empty");//"No name defined" is the default value.
        return pass;
    }
    public void registrar(String us,String pass){
        SharedPreferences.Editor editor = getSharedPreferences("mp", MODE_PRIVATE).edit();
        editor.putString("user", us);
        editor.putString("pass", pass);
        editor.apply();
    }
}
