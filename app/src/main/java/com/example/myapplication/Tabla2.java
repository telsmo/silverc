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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class Tabla2 {
    private DatabaseHelper databaseHandler;
    private TableLayout tabla; // Layout donde se pintará la tabla
    private ArrayList<TableRow> filas; // Array de las filas de la tabla
    private Activity actividad;
    private Resources rs;
    private int FILAS, COLUMNAS; // Filas y columnas de nuestra tabla

    public Tabla2(Activity actividad, TableLayout tabla, String name)
    {
        databaseHandler = new DatabaseHelper(actividad, name);
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
            layoutCelda = new TableRow.LayoutParams((TableRow.LayoutParams.WRAP_CONTENT), TableRow.LayoutParams.WRAP_CONTENT);
            if (i==3){
                texto.setText(arraycabecera[i]+"        ");
            }else{texto.setText(arraycabecera[i]);}

            texto.setGravity(Gravity.CENTER);
            if (i==4 || i==2 || i==3){
                texto.setGravity(Gravity.LEFT);
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
        TextView texto = new TextView(actividad);
        layoutCelda = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);

        texto.setGravity(Gravity.CENTER);
        texto.setBackgroundColor(ContextCompat.getColor(actividad, R.color.prim3));
        texto.setTextColor(ContextCompat.getColor(actividad,R.color.prim1));
        texto.setLayoutParams(layoutCelda);
        texto.setTextSize(22);
        fila.addView(texto);
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
        String aux;
        Button[] texto = new Button[col*fil];
        int i2;
        int i;
        for(i = 0; i< elementos.size()-1; i++)
        {
            i2=i*actual;
            texto[i2] = new Button(actividad);
            texto[i2].setText(String.valueOf(elementos.get(i)));
            aux=elementos.get(i)+"&"+elementos.get(elementos.size()-1)+"&"+i+"&"+elementos.get(4)+"&"+elementos.get(0)+"&"+elementos.get(6);

            texto[i2].setGravity(Gravity.CENTER /*| Gravity.LEFT*/);
            if (i==4 || i==2 || i==3){
                texto[i2].setGravity(Gravity.LEFT);
                texto[i2].setGravity(Gravity.CENTER_VERTICAL);
            }else{
                texto[i2].setGravity(Gravity.CENTER);
            }
            if (i==0 || i==4 || i==5){
                texto[i2].setEnabled(false);
            }
            if (i==2 || i==4){
                aux=aux+"&si";
            }else{
                aux=aux+"&no";
            }
            texto[i2].setTag(aux);
            texto[i2].setTextColor(ContextCompat.getColor(actividad,R.color.prim2));
            texto[i2].setTextSize(15);
            texto[i2].setBackgroundColor(ContextCompat.getColor(actividad, R.color.prim0));
            texto[i2].setOnClickListener(popupea2);
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(texto[i2].getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
            texto[i2].setLayoutParams(layoutCelda);

            fila.addView(texto[i2]);
        }
        Button eliminar = new Button(actividad);
        eliminar.setTag(aux=elementos.get(1)+"&"+elementos.get(elementos.size()-1)+"&"+i+"&"+elementos.get(4)+"&"+elementos.get(0)+"&"+elementos.get(6));
        eliminar.setWidth(5);
        eliminar.setHeight(5);
        eliminar.setText("X");
        eliminar.setTextColor(ContextCompat.getColor(actividad,R.color.prim0));
        eliminar.setBackgroundResource(R.drawable.ic_delete_black_24dp);
        eliminar.setLayoutParams(new TableRow.LayoutParams(40,100));
        eliminar.setTextSize(20);
        eliminar.setOnClickListener(popDel);
        fila.addView(eliminar);
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
            show_popup(v,v.getTag().toString());
        }
    };

    public void show_popup (View view, String actual){
        TextView textView = new TextView(actividad);
        final String[] aux= actual.split("&");
        textView.setText("Modificar: ");
        textView.setPadding(20, 30, 20, 30);
        //textView.setTextSize(20F);
        //textView.setTextColor(getResources().getColor(R.color.white));
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setCustomTitle(textView);
        final EditText input = new EditText(actividad);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        if(aux[6].equals("si")){
            StringBuilder precios = new StringBuilder(aux[0]); // removing first character
            precios.deleteCharAt(0);
            input.setText(precios);
        }else{
            input.setText(aux[0]);
        }
        input.setPadding(20, 30, 20, 30);
        //input.setTextColor((getResources().getColor(R.color.colorAccent)));
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHandler.updateMov(aux[1],input.getText().toString(),aux[2],aux[4],aux[5]);
                actividad.finish();
                Intent intento = actividad.getIntent();
                actividad.startActivity(intento);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
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
    View.OnClickListener popDel =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            show_popup_del(v,v.getTag().toString());
        }
    };
    public void show_popup_del (View view, String actual){
        TextView textView = new TextView(actividad);
        final String[] aux= actual.split("&");
        //textView.setTextSize(20F);
        //textView.setTextColor(getResources().getColor(R.color.white));
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setCustomTitle(textView);
        final TextView input = new TextView(actividad);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setText("Esta seguro que desea eliminar esta fila?");
        input.setPadding(20, 30, 20, 30);
        //input.setTextColor((getResources().getColor(R.color.colorAccent)));
        builder.setView(input);

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHandler.deleteMov(aux[1],aux[3],aux[2],aux[4],aux[5]);
                actividad.finish();
                Intent intento = actividad.getIntent();
                actividad.startActivity(intento);
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
}
