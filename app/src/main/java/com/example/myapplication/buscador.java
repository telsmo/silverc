package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class buscador extends AppCompatActivity {
    private DatabaseHelper databaseHandler;
    TableLayout table;
    List<String> lista;
    String[] str;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        name = intent.getExtras().getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscador);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        lista = new ArrayList<>();
        str = new String[] {"Tipo", "Nombre", "Categoria", "Bolsillo"};
        Collections.addAll(lista, str);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, lista);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla), name);
        tabla.agregarCabecera(R.array.cabecera_tabla);
        Cursor datos = databaseHandler.getTableMov();
        if (datos.getCount() == 0) {
            Toast.makeText(this, "La tabla esta vacía", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            int x= datos.getColumnCount();
            int y= datos.getCount();
            while (datos.moveToNext()) {
                i=i+1;
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(datos.getString(1));
                elementos.add(datos.getString(2));
                elementos.add("$"+datos.getString(4));
                elementos.add(datos.getString(7));
                elementos.add("$"+(datos.getInt(4))*datos.getInt(7));
                elementos.add(datos.getString(3));
                elementos.add(datos.getString(6));
                elementos.add(datos.getString(5));
                elementos.add(datos.getString(0));
                tabla.agregarFilaTabla(elementos,x,y,i);
            }
        }

    }
    public void buscar (View view){
        final EditText c2 = (EditText) findViewById(R.id.editText);
        final Spinner c3 = (Spinner) findViewById(R.id.spinner2);
        String c13 = c3.getSelectedItem().toString();
        String c12 = c2.getText().toString();
        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla), name);
        table = (TableLayout)findViewById(R.id.tabla);

        if (c13=="Tipo"){
            c13="mov";
        }else if (c13=="Nombre"){
            c13="nombre_mov";
        }else if (c13=="Categoria"){
            c13="categoria";
        }else if(c13=="Bolsillo"){
            c13="bolsillo";
        }
        Cursor datos= databaseHandler.getBuscador(c12,c13);
        if (datos.getCount() == 0) {
            Toast.makeText(this, "La tabla esta vacía", Toast.LENGTH_LONG).show();
        } else {

            table.removeAllViews();
            tabla.agregarCabecera(R.array.cabecera_tabla);
            int i = 0;
            int x= datos.getColumnCount();
            int y= datos.getCount();
            while (datos.moveToNext()) {
                i=i+1;
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(datos.getString(1));
                elementos.add(datos.getString(2));
                elementos.add("$"+datos.getString(4));
                elementos.add(datos.getString(7));
                elementos.add("$"+(datos.getInt(4))*datos.getInt(7));
                elementos.add(datos.getString(3));
                elementos.add(datos.getString(6));
                elementos.add(datos.getString(5));
                elementos.add(datos.getString(0));
                tabla.agregarFilaTabla(elementos,x,y,i);
            }
        }

    }
    public void vertodo (View view){
        table = (TableLayout)findViewById(R.id.tabla);
        table.removeAllViews();
        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla), name);
        tabla.agregarCabecera(R.array.cabecera_tabla);
        Cursor datos = databaseHandler.getTableMov();
        if (datos.getCount() == 0) {
            Toast.makeText(this, "La tabla esta vacía", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            int x= datos.getColumnCount();
            int y= datos.getCount();
            while (datos.moveToNext()) {
                i=i+1;
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(datos.getString(1));
                elementos.add(datos.getString(2));
                elementos.add("$"+datos.getString(4));
                elementos.add(datos.getString(7));
                elementos.add("$"+(datos.getInt(4))*datos.getInt(7));
                elementos.add(datos.getString(3));
                elementos.add(datos.getString(6));
                elementos.add(datos.getString(5));
                elementos.add(datos.getString(0));
                tabla.agregarFilaTabla(elementos,x,y,i);
            }
        }
    }

}