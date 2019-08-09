package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Cargar extends Activity {
    private DatabaseHelper databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("namexd");
        String CallingActivity = extras.getString("CallingActivity");
        databaseHandler = new DatabaseHelper(this, name);
        setContentView(R.layout.cargar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2= findViewById(R.id.spinner);
        String[] categorias = new String[]{
                "Entretenimiento", "Comida", "Transporte", "Impuestos", "Otros"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categorias);
        //String[] bolsilloxd = new String[]{
        //        "Entretenimiento", "Comida", "Transporte", "Impuestos", "Otros"};
        ArrayList<String> bolsilloxd = databaseHandler.getbolsillos();
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, bolsilloxd);
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner2.setAdapter(spinnerArrayAdapter2);
        spinner.setAdapter(spinnerArrayAdapter);
    }
    public void cargar (View view){
        final EditText c1 = (EditText) findViewById(R.id.c1);
        final EditText c2 = (EditText) findViewById(R.id.c2);
        final Spinner c3 = (Spinner) findViewById(R.id.spinner1);
        final EditText c4 = (EditText) findViewById(R.id.c4);
        final EditText c5 = (EditText) findViewById(R.id.c5);
        final Spinner c6 = (Spinner) findViewById(R.id.spinner);
        String c11 = c1.getText().toString();
        String c12 = c2.getText().toString();
        String c13 = c3.getSelectedItem().toString();
        String c14 = c4.getText().toString();
        String c15 = c5.getText().toString();
        String c16 = c6.getSelectedItem().toString();

        String result= databaseHandler.addMov("Compra",c11,Integer.parseInt(c12),c13,c16,Integer.parseInt(c14));
        //Integer cant= Integer.parseInt(c12)*Integer.parseInt(c14);
        Toast.makeText(Cargar.this,result,Toast.LENGTH_LONG).show();
        finish();

    }
}