package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class bolsillo extends Activity {
    private DatabaseHelper databaseHandler;
    private String name;
    ArrayList<registros> la_lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getExtras().getString("nombre");
        setContentView(R.layout.bolsillo);
        databaseHandler = new DatabaseHelper(this, name);
        mitablita2();
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
        //Toast.makeText(this,Long.toString(result),Toast.LENGTH_LONG).show();
        String movcreacion= databaseHandler.addMov("Ingreso","Creacion del bolsillo "+cb1,cb3,"Carga",cb1,1);
        finish();
    }
    public void mitablita2() {

        ArrayList<registros> reg1;
        ListView listView;
        registros registro2;
        ListView lista =findViewById(R.id.listview7);
        databaseHandler = new DatabaseHelper(this,name);
        //ArrayList<String> la_lista = new ArrayList<>();
        la_lista = new ArrayList<>();
        Cursor datos3 = databaseHandler.getTableBol();
        if (datos3.getCount() == 0){
            Toast.makeText(bolsillo.this,"La tabla esta vacía",Toast.LENGTH_LONG).show();
        }else{
            int i=0;

            while (datos3.moveToNext()){
                registro2 = new registros(datos3.getString(1),datos3.getString(2),"$"+Integer.toString(datos3.getInt(3)));
                //la_lista.add(datos.getString(1));
                la_lista.add(i,registro2);
                //ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,la_lista);
                //lista.setAdapter(listAdapter);
                System.out.println(datos3.getString(1)+" "+datos3.getString(2)+" "+Integer.toString(datos3.getInt(3)));
                System.out.println(la_lista.get(i).getuno());
                i++;
            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.columnas, la_lista);
            listView = (ListView) findViewById(R.id.listview7);
            listView.setAdapter(adapter);
            TextView bruh = (TextView) findViewById(R.id.textView25);
            Integer result= databaseHandler.getSaldoTotal();
            String total= Integer.toString(result);
            total="$"+total;
            bruh.setText(total);
        }
    }
}
