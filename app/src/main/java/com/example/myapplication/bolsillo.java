package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class bolsillo extends Activity {
    private DatabaseHelper databaseHandler;
    Intent intent = getIntent();
    String name = intent.getExtras().getString("nombre");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHandler = new DatabaseHelper(this, name);
        setContentView(R.layout.bolsillo);
    }

    public void gotocrearbol (View view){
        Intent bol = new Intent(this,CargarBolsillo.class);
        bol.putExtra("nombre",name);
        startActivity(bol);
    }
}
