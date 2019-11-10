package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
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

public class TOP {
    private TableLayout tabla; // Layout donde se pintará la tabla
    private ArrayList<TableRow> filas; // Array de las filas de la tabla
    private ArrayList<TableRow> filas2; // Array de las filas de la tabla
    private Activity actividad;
    private Resources rs;
    private int FILAS, COLUMNAS; // Filas y columnas de nuestra tabla
    private int PUESTO;

    public TOP(Activity actividad, TableLayout tabla)
    {
        this.actividad = actividad;
        this.tabla = tabla;
        rs = this.actividad.getResources();
        FILAS = COLUMNAS = 0;
        filas = new ArrayList<TableRow>();
        filas2 = new ArrayList<TableRow>();
    }
    public void agregarCabecera(int recursocabecera)
    {

        TableRow.LayoutParams layoutCelda;
        TableRow fila = new TableRow(actividad);
        TableRow fila2 = new TableRow(actividad);
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        fila.setLayoutParams(layoutFila);
        String[] arraycabecera = rs.getStringArray(recursocabecera);
        COLUMNAS = arraycabecera.length;

        for(int i = 0; i < arraycabecera.length; i++)
        {

            TextView texto = new TextView(actividad);
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(arraycabecera[i]), TableRow.LayoutParams.WRAP_CONTENT);
            texto.setText(arraycabecera[i]);
            texto.setGravity(Gravity.LEFT);
            texto.setBackgroundColor(00000000);
            texto.setTextColor(ContextCompat.getColor(actividad,R.color.prim1));
            texto.setLayoutParams(layoutCelda);
            texto.setTextSize(13);
            texto.setTypeface(texto.getTypeface(), Typeface.BOLD);
            texto.getBackground().setAlpha(00);
            fila.addView(texto);
        }
        tabla.addView(fila);
        filas.add(fila);

        FILAS++;
    }
    public void agregarFilaTabla(ArrayList<String> elementos,String opcion)
    {
        TableRow.LayoutParams layoutCelda;
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow fila = new TableRow(actividad);
        fila.setLayoutParams(layoutFila);
        PUESTO++;
        for(int i = 0; i< elementos.size(); i++)
        {
            TextView texto;
            if (opcion=="c"){
                if (i==0 || i==3){
                    texto = new Button(actividad);
                    texto.setTextColor(ContextCompat.getColor(actividad,R.color.prim3));
                }else{
                    texto = new TextView(actividad);
                    texto.setTextColor(ContextCompat.getColor(actividad,R.color.prim2));
                }
            }
            else{
                if (i==0 || i==4){
                    texto = new Button(actividad);
                    texto.setTextColor(ContextCompat.getColor(actividad,R.color.prim3));
                }else{
                    texto = new TextView(actividad);
                    texto.setTextColor(ContextCompat.getColor(actividad,R.color.prim2));
            }
            }
            texto.setGravity(Gravity.LEFT);
            if (i==3 || i==4){
                if (opcion.equals("c") && i==4){
                    texto.setText(String.valueOf(elementos.get(i)));
                }else {
                    texto.setText("$"+String.valueOf(elementos.get(i)));}
            }else{
                texto.setText(String.valueOf(elementos.get(i)));
            }

            texto.setTextSize(14);
            texto.setBackgroundColor(ContextCompat.getColor(actividad, R.color.prim0));
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(texto.getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
            texto.setLayoutParams(layoutCelda);
            texto.getBackground().setAlpha(00);
            texto.setLayoutParams(layoutCelda);

            fila.addView(texto);
        }

        tabla.addView(fila);
        filas.add(fila);

        FILAS++;
    }


    private int obtenerAnchoPixelesTexto(String texto)
    {
        Paint p = new Paint();
        Rect bounds = new Rect();
        p.setTextSize(25);
        p.getTextBounds(texto, 0, texto.length(), bounds);
        return bounds.width();
    }
}
