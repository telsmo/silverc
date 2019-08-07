package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Ingreso extends Activity {
    private DatabaseHelper databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
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
}