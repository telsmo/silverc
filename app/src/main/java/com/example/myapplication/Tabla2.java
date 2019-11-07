package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Tabla2 {
    private TableLayout tabla; // Layout donde se pintará la tabla
    private ArrayList<TableRow> filas; // Array de las filas de la tabla
    private Activity actividad;
    private Resources rs;
    private int FILAS, COLUMNAS; // Filas y columnas de nuestra tabla

    public Tabla2(Activity actividad, TableLayout tabla)
    {
        this.actividad = actividad;
        this.tabla = tabla;
        rs = this.actividad.getResources();
        FILAS = COLUMNAS = 0;
        filas = new ArrayList<TableRow>();
    }
    public void agregarCabecera(int recursocabecera)
    {
        TableRow.LayoutParams layoutCelda;
        TableRow fila = new TableRow(actividad);
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        fila.setLayoutParams(layoutFila);
        String[] arraycabecera = rs.getStringArray(recursocabecera);
        COLUMNAS = arraycabecera.length;

        for(int i = 0; i < arraycabecera.length; i++)
        {

            TextView texto = new TextView(actividad);
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(arraycabecera[i]), TableRow.LayoutParams.WRAP_CONTENT);
            if (i==3){
                texto.setText(arraycabecera[i]+"        ");
            }else{texto.setText(arraycabecera[i]);}

            texto.setGravity(Gravity.CENTER);
            if (i==4 || i==2 || i==3){
                texto.setGravity(Gravity.RIGHT);
                texto.setGravity(Gravity.CENTER_VERTICAL);
            }else{
                texto.setGravity(Gravity.CENTER);
            }
            texto.setBackgroundColor(ContextCompat.getColor(actividad, R.color.prim3));
            texto.setTextColor(ContextCompat.getColor(actividad,R.color.prim1));
            texto.setLayoutParams(layoutCelda);
            texto.setTextSize(22);
            fila.addView(texto);
        }

        tabla.addView(fila);
        filas.add(fila);

        FILAS++;
    }
    public void agregarFilaTabla(ArrayList<String> elementos)
    {
        TableRow.LayoutParams layoutCelda;
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow fila = new TableRow(actividad);
        fila.setLayoutParams(layoutFila);

        for(int i = 0; i< elementos.size(); i++)
        {
            Button texto = new Button(actividad);
            if (i==4 || i==2 || i==3){
                texto.setGravity(Gravity.RIGHT);
                texto.setGravity(Gravity.CENTER_VERTICAL);
            }else{
                texto.setGravity(Gravity.CENTER);
            }
            texto.setText(String.valueOf(elementos.get(i)));
            texto.setTextColor(ContextCompat.getColor(actividad,R.color.prim2));
            texto.setTextSize(15);
            texto.setBackgroundColor(ContextCompat.getColor(actividad, R.color.prim0));
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(texto.getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
            texto.setLayoutParams(layoutCelda);
            texto.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    toast();
                }
            });

            texto.getBackground().setAlpha(120);

            fila.addView(texto);
        }

        tabla.addView(fila);
        filas.add(fila);

        FILAS++;
    }
    private void toast(){
        Toast.makeText(actividad, "Toque 'Editar' y ingrese su contraseña para editar sus datos", Toast.LENGTH_LONG).show();

    }

    private int obtenerAnchoPixelesTexto(String texto)
    {
        Paint p = new Paint();
        Rect bounds = new Rect();
        p.setTextSize(50);

        p.getTextBounds(texto, 0, texto.length(), bounds);
        return bounds.width();
    }
}
