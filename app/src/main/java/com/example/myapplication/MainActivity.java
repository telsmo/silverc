package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
        private DatabaseHelper databaseHandler;
        public String name;
        ArrayList<registros> la_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(sesion_iniciada()){
            name = nom_us() + contra();
            mitablita();
        }else{
            setContentView(R.layout.activity_main);
        }
       /* Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

    }



    public void gotomainlolinicial (View view){
//agarro lo de user y lo pongo en la variable name, para despues crear la base con ese nombre
        final EditText us = (EditText) findViewById(R.id.nom_us);
        final EditText ps = (EditText) findViewById(R.id.contra);

        String user = us.getText().toString();
        String pass = ps.getText().toString();
        String name= user+pass;
        //guarda en datos el user y la password
        registrar(user,pass);

        setContentView(R.layout.mainlol);
        databaseHandler = new DatabaseHelper(this, name);
    }

    //seleccionar para ir a cargar un gasto o ingreso.
    //antes de ttodo esto, fijate que en AndroidManifest.xml pongo todas las nuevas clases, si no, no funciona la nueva actividad y crashea la app
    public void gotocargar (View view){
    //siempre antes de pasar a otra screen se ingresa una intención. Con esto le decimos a android qué .java o actividad queremos iniciar
        Intent getNameScreenIntent= new Intent(this,
        Cargar.class);
        //No se puede pasar el handler de la base por parámetros, entonces pasamos los datos necesarios por parámetros para poder volver a pedir el handler
        // en la actividad que queremos iniciar.
        String name = nom_us() + contra();
        //Bundle nos permite empaquetar varios strings, esto nos sirve para pasar multiples parámetros
        Bundle extras = new Bundle();
        //acá el "callingactivity" no era necesario, pero, de nuevo, era para ejemplificar como se podían pasar multiples parámetros por un solo "putextra"
        extras.putString("CallingActivity","MainActivity");
        extras.putString("namexd",name);
        //acá se tiene que notar que se pone .putExtras, porque es un bundle
        getNameScreenIntent.putExtras(extras);
        //startActivity usa el intent que declaramos antes para empezar .Cargar
        startActivity(getNameScreenIntent);

    }
    public void gotoingreso (View view){
        Intent getNameScreenIntent= new Intent(this,
                Ingreso.class);
        String name = nom_us() + contra();
        //acá un ejemplo pasando sólo un parametro, entonces sólo se pone putExtra, (1)
        getNameScreenIntent.putExtra("namexd",name);
        startActivity(getNameScreenIntent);

    }

    ///////////////////////////////////////////////////////////////////////////////
    //SE APRETA EL BOTON DE BOLSILLO
    public void gotobolsillo(View view){
        Intent bol = new Intent(this,bolsillo.class);
        String name = nom_us() + contra();
        bol.putExtra("nombre",name);
        startActivity(bol);
    }
    public void gotomainlol (View view){

        setContentView(R.layout.mainlol);

    }

    public boolean sesion_iniciada() {
        boolean a;
        SharedPreferences prefs = getSharedPreferences("mp", MODE_PRIVATE);
        String user = prefs.getString("user", "empty");//"empty" is the default value.
        if (user!="empty"){
            a=true;
        }else{
            a=false;
        }
        return a;
    }
    public String nom_us(){
        SharedPreferences prefs = getSharedPreferences("mp", MODE_PRIVATE);
        String nom = prefs.getString("user", "empty");//"No name defined" is the default value.
        return nom;
        }
    public String contra(){
        SharedPreferences prefs = getSharedPreferences("mp", MODE_PRIVATE);
        String pass = prefs.getString("pass", "empty");//"No name defined" is the default value.
        return pass;
    }
    public void registrar(String us,String pass){
        SharedPreferences.Editor editor = getSharedPreferences("mp", MODE_PRIVATE).edit();
        editor.putString("user", us);
        editor.putString("pass", pass);
        editor.apply();


    }

    public void mitablita() {
        setContentView(R.layout.mainlol);
        ArrayList<registros> reg1;
        ListView listView;
        registros registro;
        ListView lista =findViewById(R.id.listview);
        databaseHandler = new DatabaseHelper(this,name);

        //ArrayList<String> la_lista = new ArrayList<>();
        la_lista = new ArrayList<>();
        Cursor datos = databaseHandler.getTableMov();
        if (datos.getCount() == 0){
            Toast.makeText(MainActivity.this,"La tabla esta vacía",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while (datos.moveToNext()){
                registro = new registros(datos.getString(1),datos.getString(2),datos.getString(4));
                //la_lista.add(datos.getString(1));
                la_lista.add(i,registro);
                //ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,la_lista);
                //lista.setAdapter(listAdapter);
                System.out.println(datos.getString(1)+" "+datos.getString(2)+" "+datos.getString(4));
                System.out.println(la_lista.get(i).getuno());
                i++;
            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.columnas, la_lista);
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);
        }
    }
}
