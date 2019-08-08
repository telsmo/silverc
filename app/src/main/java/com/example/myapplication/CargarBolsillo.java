package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CargarBolsillo extends Activity {
        private DatabaseHelper databaseHandler;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();
            String name = intent.getExtras().getString("nombre");
            databaseHandler = new DatabaseHelper(this, name);
            setContentView(R.layout.crearbolsillo);
        }
    public void agregar_bol (View view){
        final EditText b1 = (EditText) findViewById(R.id.cb1);
        final EditText b2 = (EditText) findViewById(R.id.cb2);
        String cb1 = b1.getText().toString();
        String cb2 = b2.getText().toString();
        long result= databaseHandler.addBol("a",2);
        Toast.makeText(CargarBolsillo.this,Long.toString(result),Toast.LENGTH_LONG).show();
        finish();
    }
}
