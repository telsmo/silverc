package com.example.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cargar extends Activity {
    private DatabaseHelper databaseHandler;
    private String m_Text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("namexd");
        String CallingActivity = extras.getString("CallingActivity");
        databaseHandler = new DatabaseHelper(this, name);
        setContentView(R.layout.cargar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2= findViewById(R.id.spinner);
        String[] categorias = new String[]{
                "Entretenimiento", "Comida", "Transporte", "Impuestos", "Otros"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categorias);
        //String[] bolsilloxd = new String[]{
        //        "Entretenimiento", "Comida", "Transporte", "Impuestos", "Otros"};
        ArrayList<String> bolsilloxd = databaseHandler.getbolsillos();
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, bolsilloxd);
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner2.setAdapter(spinnerArrayAdapter2);
        spinner.setAdapter(spinnerArrayAdapter);
    }
    public void cargar (View view){
        final EditText c1 = (EditText) findViewById(R.id.c1);
        final EditText c2 = (EditText) findViewById(R.id.c2);
        final Spinner c3 = (Spinner) findViewById(R.id.spinner1);
        final EditText c4 = (EditText) findViewById(R.id.c4);
        final EditText c5 = (EditText) findViewById(R.id.c5);
        final Spinner c6 = (Spinner) findViewById(R.id.spinner);
        String c11 = c1.getText().toString();
        String c12 = c2.getText().toString();
        String c13 = c3.getSelectedItem().toString();
        String c14 = c4.getText().toString();
        String c15 = c5.getText().toString();
        String c16 = c6.getSelectedItem().toString();

        String result= databaseHandler.addMov("Compra",c11,Integer.parseInt(c12),c13,c16,Integer.parseInt(c14));
        //Integer cant= Integer.parseInt(c12)*Integer.parseInt(c14);
        //Toast.makeText(Cargar.this,result,Toast.LENGTH_LONG).show();
        finish();

    }
    public void show_popup (View view){
        TextView textView = new TextView(this);
        textView.setText("Crear Categoría");
        textView.setPadding(20, 30, 20, 30);
        //textView.setTextSize(20F);
        textView.setTextColor(Color.rgb(0,204,153));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCustomTitle(textView);
// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setPadding(20, 30, 20, 30);
        input.setTextColor(Color.rgb(204,51,51));
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog bg = builder.show();
        bg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(0,0,51)));
        bg.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(204,51,51));
        bg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(204,51,51));
    }
}