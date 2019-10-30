package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class Tabla {
    private TableLayout tabla; // Layout donde se pintará la tabla
    private ArrayList<TableRow> filas; // Array de las filas de la tabla
    private Activity actividad;
    private Resources rs;
    private int FILAS, COLUMNAS; // Filas y columnas de nuestra tabla

    public Tabla(Activity actividad, TableLayout tabla)
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
            texto.setText(arraycabecera[i]);
            texto.setGravity(Gravity.CENTER);
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
    public void agregarFilaTabla(ArrayList<String> elementos, int col,int fil,int actual)
    {
        TableRow.LayoutParams layoutCelda;
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow fila = new TableRow(actividad);
        fila.setLayoutParams(layoutFila);

        Button[] texto = new Button[col*fil];
        int i2;
        for(int i = 0; i< elementos.size(); i++)
        {
            i2=i*actual;
            texto[i2] = new Button(actividad);
            texto[i2].setText(String.valueOf(elementos.get(i)));
            texto[i2].setGravity(Gravity.CENTER /*| Gravity.LEFT*/);
            texto[i2].setTextColor(ContextCompat.getColor(actividad,R.color.prim2));
            texto[i2].setTextSize(18);
            texto[i2].setBackgroundColor(ContextCompat.getColor(actividad, R.color.prim0));
            texto[i2].setOnClickListener(popupea2);
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(texto[i].getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
            texto[i2].setLayoutParams(layoutCelda);

            fila.addView(texto[i]);
        }

        tabla.addView(fila);
        filas.add(fila);

        FILAS++;
    }
    private int obtenerAnchoPixelesTexto(String texto)
    {
        Paint p = new Paint();
        Rect bounds = new Rect();
        p.setTextSize(50);

        p.getTextBounds(texto, 0, texto.length(), bounds);
        return bounds.width();
    }

    View.OnClickListener popupea2 =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            show_popup(v,v.getId());
        }
    };

    public void show_popup (View view, final int id){
        TextView textView = new TextView(actividad);
        textView.setText("Modificar: " + id);
        textView.setPadding(20, 30, 20, 30);
        //textView.setTextSize(20F);
        //textView.setTextColor(getResources().getColor(R.color.white));
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setCustomTitle(textView);
        final EditText input = new EditText(actividad);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setPadding(20, 30, 20, 30);
        //input.setTextColor((getResources().getColor(R.color.colorAccent)));
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //si es positivo poner el codigo aqui xd
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog bg = builder.show();
        //bg.getWindow().setBackgroundDrawable(new ColorDrawable((getResources().getColor(R.color.colorPrimaryDark))));
        //bg.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor((getResources().getColor(R.color.colorAccent)));
        //bg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor((getResources().getColor(R.color.colorPrimary)));
    }

}
