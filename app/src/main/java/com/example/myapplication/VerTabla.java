package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VerTabla extends AppCompatActivity {
    String name=getIntent().getStringExtra("name");
    DatabaseHelper db;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlol);
        ListView lista =findViewById(R.id.listview);
        db = new DatabaseHelper(this,name);

        ArrayList<String> la_lista = new ArrayList<>();
        Cursor datos = db.getTableMov();
        if (datos.getCount() == 0){
            Toast.makeText(VerTabla.this,"La tabla esta vacía",Toast.LENGTH_LONG).show();
        }else{
            while (datos.moveToNext()){
                la_lista.add(datos.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,R.layout.mainlol,la_lista);
                lista.setAdapter(listAdapter);
            }
        }
    }
}
