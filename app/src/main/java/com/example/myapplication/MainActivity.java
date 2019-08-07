package com.example.myapplication;

import android.content.Intent;
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
       /* Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

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
    //antes de ttodo esto, fijate que en AndroidManifest.xml pongo todas las nuevas clases, si no, no funciona la nueva actividad y crashea la app
    public void gotocargar (View view){
    //siempre antes de pasar a otra screen se ingresa una intención. Con esto le decimos a android qué .java o actividad queremos iniciar
        Intent getNameScreenIntent= new Intent(this,
        Cargar.class);
        //No se puede pasar el handler de la base por parámetros, entonces pasamos los datos necesarios por parámetros para poder volver a pedir el handler
        // en la actividad que queremos iniciar.
        String name = nom_us() + contra();
        //Bundle nos permite empaquetar varios strings, esto nos sirve para pasar multiples parámetros
        Bundle extras = new Bundle();
        //acá el "callingactivity" no era necesario, pero, de nuevo, era para ejemplificar como se podían pasar multiples parámetros por un solo "putextra"
        extras.putString("CallingActivity","MainActivity");
        extras.putString("namexd",name);
        //acá se tiene que notar que se pone .putExtras, porque es un bundle
        getNameScreenIntent.putExtras(extras);
        //startActivity usa el intent que declaramos antes para empezar .Cargar
        startActivity(getNameScreenIntent);

    }
    public void gotoingreso (View view){
        Intent getNameScreenIntent= new Intent(this,
                Ingreso.class);
        String name = nom_us() + contra();
        //acá un ejemplo pasando sólo un parametro, entonces sólo se pone putExtra, (1)
        getNameScreenIntent.putExtra("namexd",name);
        startActivity(getNameScreenIntent);

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
