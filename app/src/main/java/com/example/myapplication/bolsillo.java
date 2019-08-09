package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class bolsillo extends Activity {
    private DatabaseHelper databaseHandler;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getExtras().getString("nombre");
        databaseHandler = new DatabaseHelper(this, name);
        setContentView(R.layout.bolsillo);
    }

    public void gotocrearbol (View view){
        setContentView(R.layout.crearbolsillo);
    }
    public void agregar_bol (View view){
        final EditText b1 = (EditText) findViewById(R.id.cb1);
        final EditText b2 = (EditText) findViewById(R.id.cb2);
        final EditText b3 = (EditText) findViewById(R.id.cb3);
        String cb1 = b1.getText().toString();
        String cb2 = b2.getText().toString();
        String wea =b3.getText().toString();
        Integer cb3 = Integer.parseInt(wea);
        long result= databaseHandler.addBol(cb1,cb2,cb3);
        Toast.makeText(this,Long.toString(result),Toast.LENGTH_LONG).show();
        String movcreacion= databaseHandler.addMov("Ingreso","Creacion del bolsillo "+cb1,cb3,"carga",cb1,1);
        setContentView(R.layout.bolsillo);
    }
}
