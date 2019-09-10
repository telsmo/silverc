package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.widget.Toast;



public class buscador extends AppCompatActivity {
    private DatabaseHelper databaseHandler;
    TableLayout table;
    List<String> lista;
    String[] str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String name = intent.getExtras().getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscador);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        lista = new ArrayList<>();
        str = new String[] {"mov", "nombre_mov", "categoria", "bolsillo"};
        Collections.addAll(lista, str);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, lista);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        Cursor datos = databaseHandler.getTableMov();
        if (datos.getCount() == 0) {
            Toast.makeText(this, "La tabla esta vacía", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (datos.moveToNext()) {
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(datos.getString(1));
                elementos.add(datos.getString(2));
                elementos.add("$"+datos.getString(4));
                elementos.add(datos.getString(7));
                elementos.add("$"+(datos.getInt(4))*datos.getInt(7));
                elementos.add(datos.getString(3));
                elementos.add(datos.getString(6));
                elementos.add(datos.getString(5));
                tabla.agregarFilaTabla(elementos);
            }
        }

    }
    public void buscar (View view){
        final EditText c2 = (EditText) findViewById(R.id.editText);
        final Spinner c3 = (Spinner) findViewById(R.id.spinner2);
        String c13 = c3.getSelectedItem().toString();
        String c12 = c2.getText().toString();
        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla));
        table = (TableLayout)findViewById(R.id.tabla);
        Cursor datos= databaseHandler.getBuscador(c12,c13);
        if (datos.getCount() == 0) {
            Toast.makeText(this, "La tabla esta vacía", Toast.LENGTH_LONG).show();
        } else {

            table.removeAllViews();
            tabla.agregarCabecera(R.array.cabecera_tabla);
            while (datos.moveToNext()) {
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(datos.getString(1));
                elementos.add(datos.getString(2));
                elementos.add("$"+datos.getString(4));
                elementos.add(datos.getString(7));
                elementos.add("$"+(datos.getInt(4))*datos.getInt(7));
                elementos.add(datos.getString(3));
                elementos.add(datos.getString(6));
                elementos.add(datos.getString(5));
                tabla.agregarFilaTabla(elementos);
            }
        }

    }
    public void vertodo (View view){
        table = (TableLayout)findViewById(R.id.tabla);
        table.removeAllViews();
        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        Cursor datos = databaseHandler.getTableMov();
        if (datos.getCount() == 0) {
            Toast.makeText(this, "La tabla esta vacía", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (datos.moveToNext()) {
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(datos.getString(1));
                elementos.add(datos.getString(2));
                elementos.add("$"+datos.getString(4));
                elementos.add(datos.getString(7));
                elementos.add("$"+(datos.getInt(4))*datos.getInt(7));
                elementos.add(datos.getString(3));
                elementos.add(datos.getString(6));
                elementos.add(datos.getString(5));
                tabla.agregarFilaTabla(elementos);
            }
        }
    }

}