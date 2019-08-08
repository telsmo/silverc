package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        Toast.makeText(Cargar.this,Long.toString(result),Toast.LENGTH_LONG).show();
        setContentView(R.layout.mainlol);

    }
}