package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
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
import android.widget.TextView;
import android.widget.Toast;



public class buscador2 extends AppCompatActivity {
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
        setContentView(R.layout.buscadorsinedit);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        lista = new ArrayList<>();
        str = new String[] {"Tipo", "Nombre", "Categoria", "Bolsillo"};
        Collections.addAll(lista, str);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, lista);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Tabla2 tabla = new Tabla2(this, (TableLayout) findViewById(R.id.tabla));
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

    public void popup(View view){

            TextView textView = new TextView(this);
            textView.setText("Ingrese su contraseña: ");
            textView.setPadding(20, 30, 20, 30);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCustomTitle(textView);
            final EditText eddit= new EditText(this);
            eddit.setHint("Contraseña");
            eddit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            builder.setView(eddit);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                String pass1;
                String pass2;
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pass1=contra();
                    pass2=eddit.getText().toString();
                    if (pass1.equals(pass2)){
                        Intent bol = new Intent(getApplicationContext(),buscador.class);
                        bol.putExtra("namexd",name);
                        startActivity(bol);
                        overridePendingTransition(R.transition.mina,R.transition.anim);
                    }
                    else{
                        eddit.setText("");
                        eddit.setHint("Contraseña incorrecta");
                        eddit.setHintTextColor(ContextCompat.getColor(getApplicationContext(),R.color.prim0));
                    }

                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog bg = builder.show();

    }
    public String contra(){
        SharedPreferences prefs = getSharedPreferences("mp", MODE_PRIVATE);
        String pass = prefs.getString("pass", "empty");//"No name defined" is the default value.
        return pass;
    }
    public void buscar (View view){
        final EditText c2 = (EditText) findViewById(R.id.editText);
        final Spinner c3 = (Spinner) findViewById(R.id.spinner2);
        String c13 = c3.getSelectedItem().toString();
        String c12 = c2.getText().toString();
        Tabla2 tabla = new Tabla2(this, (TableLayout) findViewById(R.id.tabla));
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
        Tabla2 tabla = new Tabla2(this, (TableLayout) findViewById(R.id.tabla));
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