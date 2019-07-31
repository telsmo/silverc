package com.example.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(sesion_iniciada()){
            setContentView(R.layout.mainlol);
            String name = nom_us() + contra();
            DatabaseHelper databaseHandler = new DatabaseHelper(this, name);
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
        DatabaseHelper databaseHandler = new DatabaseHelper(this, name);
    }
    public void gotocargar (View view){

        setContentView(R.layout.cargar);

    }
    public void gotoingreso (View view){

        setContentView(R.layout.ingreso);

    }
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
