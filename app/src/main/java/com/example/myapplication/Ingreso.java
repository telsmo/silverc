package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ingreso extends Activity {
    private DatabaseHelper databaseHandler;
    AutoCompleteTextView autocomplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        setContentView(R.layout.ingreso);
        Spinner spinner = (Spinner) findViewById(R.id.spinner3);
        ArrayList<String> categorias = databaseHandler.getbolsillos();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categorias);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        autocomplete = findViewById(R.id.i1);
        List<String> autocompletebd= databaseHandler.loadIngres();
        ArrayAdapter<String> autocompleteadapter= new ArrayAdapter<String>(this,R.layout.spinner_item,autocompletebd);
        autocomplete.setThreshold(1);
        autocomplete.setAdapter(autocompleteadapter);


    }
    public void ingresar (View view){
        final EditText i1 = (EditText) findViewById(R.id.i1);
        final EditText i2 = (EditText) findViewById(R.id.i2);
        final Spinner i6 =(Spinner) findViewById(R.id.spinner3);
        String c11 = i1.getText().toString();
        String bruh = i2.getText().toString();
        Integer c12;
        String c16 = i6.getSelectedItem().toString();

        if (c11.equals("") || (bruh.equals(""))) {
            Toast.makeText(this, "Hay uno o más datos incompletos.", Toast.LENGTH_LONG).show();
            i1.setHintTextColor(getResources().getColor(R.color.red));
            i1.setHint("Complete sus datos");
            i2.setHintTextColor(getResources().getColor(R.color.red));
            i2.setHint("Complete sus datos");
        }else{
            c12 = Integer.parseInt(bruh);
        String result= databaseHandler.addMov("Ingreso",c11,c12,"Ingreso",c16,1);
        //Toast.makeText(Ingreso.this,result,Toast.LENGTH_LONG).show();
        finish();
        }
    }
}