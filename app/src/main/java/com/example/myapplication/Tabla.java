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


public class Tabla {
    private DatabaseHelper databaseHandler;
    private TableLayout tabla; // Layout donde se pintará la tabla
    private ArrayList<TableRow> filas; // Array de las filas de la tabla
    private Activity actividad;
    private Resources rs;
    private int FILAS, COLUMNAS; // Filas y columnas de nuestra tabla

    public Tabla(Activity actividad, TableLayout tabla, String name)
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
        TextView texto = new TextView(actividad);
        layoutCelda = new TableRow.LayoutParams(80, TableRow.LayoutParams.WRAP_CONTENT);

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
            }else {texto[i2].setOnClickListener(popupea2);}
            if (i==2 || i==4){
                aux=aux+"&si";
            }else{
                aux=aux+"&no";
            }
            if (i==7){
                aux=aux+"&c";
            }else if (i==6){
                    aux=aux+"&b";
                }else{aux=aux+"&a";}

            texto[i2].setTag(aux);
            texto[i2].setTextColor(ContextCompat.getColor(actividad,R.color.prim2));
            texto[i2].setTextSize(15);
            texto[i2].setBackgroundColor(ContextCompat.getColor(actividad, R.color.prim0));
            layoutCelda = new TableRow.LayoutParams(obtenerAnchoPixelesTexto(texto[i2].getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
            texto[i2].setLayoutParams(layoutCelda);
            texto[i2].getBackground().setAlpha(120);
            fila.addView(texto[i2]);
        }
        Button eliminar = new Button(actividad);
        eliminar.setTag(aux=elementos.get(1)+"&"+elementos.get(elementos.size()-1)+"&"+i+"&"+elementos.get(4)+"&"+elementos.get(0)+"&"+elementos.get(6));
        eliminar.setWidth(5);
        eliminar.setHeight(5);
        eliminar.setText("X");
        eliminar.setTextColor(ContextCompat.getColor(actividad,R.color.prim0));
        eliminar.setBackgroundResource(R.drawable.ic_delete_black_24dp);
        eliminar.setLayoutParams(new TableRow.LayoutParams(40,80));
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
        TextView textView2 = new TextView(actividad);
        final Spinner spinner = new Spinner(actividad);
        final String[] aux= actual.split("&");
        textView.setText("Modificar: ");
        textView.setPadding(20, 30, 20, 30);
        //TOAST de evaluacion xd
        // Toast.makeText(actividad, aux[0].toString()+""+aux[1].toString()+""+aux[2].toString()+""+aux[3].toString()+""+aux[4].toString()+""+aux[5].toString()+""+aux[6].toString(), Toast.LENGTH_LONG).show();
        //textView.setTextSize(20F);
        //textView.setTextColor(getResources().getColor(R.color.white));
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setCustomTitle(textView);
        final EditText input = new EditText(actividad);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        if (aux[0].equals("Carga")|| aux[0].equals("Ingreso")){
            textView2.setText("Este dato no se puede cambiar");
            textView2.setTextColor(ContextCompat.getColor(actividad, R.color.red));
            textView2.setTextSize(16);
            textView2.setPadding(20, 0, 20, 0);
            builder.setView(textView2);
        }else{
        if(aux[6].equals("si")){
            StringBuilder precios = new StringBuilder(aux[0]); // removing first character
            precios.deleteCharAt(0);
            input.setText(precios);
        }else{
            input.setText(aux[0]);
        }
        input.setPadding(20, 30, 20, 30);
        if (aux[7].equals("a")){

            //input.setTextColor((getResources().getColor(R.color.colorAccent)));
            builder.setView(input);
        }else if (aux[7].equals("b")){
            ArrayList<String> bolsilloxd = databaseHandler.getbolsillos();
            ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(actividad, R.layout.spinner_item, bolsilloxd);
            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
            spinner.setAdapter(spinnerArrayAdapter2);
            spinner.setPadding(20, 0, 20, 0);
            builder.setView(spinner);
        } else if (aux[7].equals("c")){
            List<String> todo= databaseHandler.loadCate();
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(actividad, R.layout.spinner_item, todo);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setPadding(20, 0, 20, 0);
            builder.setView(spinner);
        }}
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String wea;
                if (aux[0].equals("Carga")|| aux[0].equals("Ingreso")){
                    dialog.cancel();
                }else{
                if (aux[7].equals("b") || aux[7].equals("c") ){
                    wea= spinner.getSelectedItem().toString();
                }else {
                    wea=input.getText().toString();
                }
                databaseHandler.updateMov(aux[1],wea,aux[2],aux[4],aux[5]);
                actividad.finish();
                Intent intento = actividad.getIntent();
                actividad.startActivity(intento);}
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
