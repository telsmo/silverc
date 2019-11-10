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



public class topclass extends AppCompatActivity {
    private DatabaseHelper databaseHandler;
    TableLayout table;
    List<String> lista;
    List<String> lista2;
    String[] str;
    String[] str2;

    String name;
    Cursor datos;
    TOP tabla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        name = intent.getExtras().getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        table=findViewById(R.id.tabla);
        tabla = new TOP(this, (TableLayout) findViewById(R.id.tabla));

        Spinner spinner = (Spinner) findViewById(R.id.spinner5);
        lista = new ArrayList<>();
        str = new String[] {"Todo el tiempo", "Último mes", "Últimos 7 días", "Hoy"};
        Collections.addAll(lista, str);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item2, lista);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item3);
        spinner.setAdapter(spinnerArrayAdapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner4);
        lista2 = new ArrayList<>();
        str2 = new String[] {"Cantidad de compras", "Total entre compras","Compras individuales"};
        Collections.addAll(lista2, str2);
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item2, lista2);
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item3);
        spinner2.setAdapter(spinnerArrayAdapter2);

        codigo(tabla);
    }
    void codigo(TOP tabla) {
        final Spinner c3 = (Spinner) findViewById(R.id.spinner5);
        String c13 = c3.getSelectedItem().toString();

        if (c13=="Todo el tiempo"){
            c13="a";
        }else if(c13=="Último mes"){
            c13="b";
        }else if(c13=="Últimos 7 días"){
            c13="c";
        }else if(c13=="Hoy"){
            c13="d";
        }
        final Spinner c4 = (Spinner) findViewById(R.id.spinner4);
        String c14 = c4.getSelectedItem().toString();
        if (c14=="Cantidad de compras"){
            c14="a";
        }else if(c14=="Total entre compras"){
            c14="b";
        }else if (c14=="Compras individuales"){
            c14="c";
        }
        if (c14=="c"){
            tabla.agregarCabecera(R.array.cabecera_top2);
        }else{
            tabla.agregarCabecera(R.array.cabecera_top);
        }
        datos = databaseHandler.getTableTOPC(c13,c14);
        if (datos.getCount() == 0) {
            Toast.makeText(this, "La tabla esta vacía", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            int x = datos.getColumnCount();
            int y = datos.getCount();
            while (datos.moveToNext()) {
                i = i + 1;
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(Integer.toString(i) + "°");
                elementos.add(datos.getString(0));
                elementos.add(datos.getString(1));
                elementos.add(datos.getString(2));
                elementos.add(datos.getString(3));
                if (c14=="c"){}else{
                elementos.add(datos.getString(4));}
                tabla.agregarFilaTabla(elementos,c14);
            }
        }
    }
    public void toast(View view){
        Toast.makeText(this, "Esta es la zona de top de gastos.                                          " +
                "Toque el símbolo a la derecha para ciclar entre las herramientas de análisis.", Toast.LENGTH_LONG).show();
    }
    public void finish(View view){

        finish();
        overridePendingTransition(R.transition.mina,R.transition.anim);
    }
    public void verTodo (View view){
        table.removeAllViews();
            codigo(tabla);
    }
}