package com.example.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class analisispiechart extends Activity {
    int amount=0;
    Cursor datos;
    Cursor datos2;
    int[] c;
    List<String> lista;
    String[] str;
    ArrayList<Integer>colores= new ArrayList<Integer>();
    ArrayList<String>categoriaseleccionadas= new ArrayList<String>();
    ArrayList<Integer>colorescategoriaseleccionadas= new ArrayList<Integer>();
    ArrayList<Integer>coriginales = new ArrayList<Integer>();
    List<String> cates;
    List<String> cates2;
    private DatabaseHelper databaseHandler;
    PieChart pieChart;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analisispiechart);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        c = new int[]{ getResources().getColor(R.color.black), getResources().getColor(R.color.blue),getResources().getColor(R.color.orange),getResources().getColor(R.color.red),getResources().getColor(R.color.dark_goldenrod),getResources().getColor(R.color.violet),getResources().getColor(R.color.deep_sky_blue),getResources().getColor(R.color.pink),getResources().getColor(R.color.green),getResources().getColor(R.color.prim3),getResources().getColor(R.color.fire_brick)};
        datos = databaseHandler.getTableMov();
        cates = databaseHandler.loadCate();
        for (int i = 0; i < c.length; i++)
            coriginales.add(new Integer(c[i]));
        for (int i = 0; i < c.length; i++)
            colores.add(new Integer(c[i]));
        codigo(datos,cates,colores);
        datos = databaseHandler.getTableMov();
        cates = databaseHandler.loadCate();
        setLista(datos,cates);
        Spinner spinner = (Spinner) findViewById(R.id.spinner5);
        lista = new ArrayList<>();
        str = new String[] {"Todo el tiempo", "Último mes", "Última semana", "Hoy"};
        Collections.addAll(lista, str);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item2, lista);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item3);
        spinner.setAdapter(spinnerArrayAdapter);

    }
    /*
    public void actualizar(View view){
        codigo();
    }int[] colores={ getResources().getColor(R.color.black), getResources().getColor(R.color.blue),getResources().getColor(R.color.orange),getResources().getColor(R.color.red),getResources().getColor(R.color.dark_goldenrod),getResources().getColor(R.color.violet),getResources().getColor(R.color.deep_sky_blue),getResources().getColor(R.color.pink),getResources().getColor(R.color.green),getResources().getColor(R.color.prim3),getResources().getColor(R.color.fire_brick)};

    */
    private void codigo(Cursor datos, List<String> cates, ArrayList<Integer> colores){
        Integer lol;
        List<Integer> catnum = new ArrayList<Integer>();
        int size = cates.size();
        for (int i = 0; i < size+1; i++)
        {
            catnum.add(0);
        }
        String a;
        //int entre,comid,trans,impue,otros=0;
        if (datos.getCount() == 0){
            Toast.makeText(this,"No hay datos",Toast.LENGTH_LONG).show();
        }else{
            while (datos.moveToNext()){

                for (int i = 0; i < size; i++)
                {
                    a = cates.get(i);
                    if (datos.getString(datos.getColumnIndex("categoria")).equals(a)){
                        lol=catnum.get(i);
                        catnum.set(i, lol+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad")))));
                    }
                }
            }}
        pieChart= (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setHoleColor(00000000);
        pieChart.setHoleRadius(40f);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setTransparentCircleAlpha(100);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {
            }
        });
        ArrayList<PieEntry> yValues = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {

                yValues.add(new PieEntry(catnum.get(i),"$"+catnum.get(i)));

        }
        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(7f);
        dataSet.setColors(colores);
        dataSet.setValueLineColor(getResources().getColor(R.color.prim1));
        PieData data= new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(getResources().getColor(R.color.prim0));




        pieChart.setData(data);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
    }
    public void verTodo (View view){
        final Spinner c3 = (Spinner) findViewById(R.id.spinner5);
        String c13 = c3.getSelectedItem().toString();
        if (c13=="Todo el tiempo"){
            c13="a";
        }else if(c13=="Último mes"){
            c13="b";
        }else if(c13=="Última semana"){
            c13="c";
        }else if(c13=="Hoy"){
            c13="d";
        }
        categoriaseleccionadas.clear();
        datos = databaseHandler.getTableMovPiechartSelection(categoriaseleccionadas,c13,"b");
        cates = databaseHandler.loadCate();
        colores.clear();
        colorescategoriaseleccionadas.clear();
        for (int i = 0; i < c.length; i++)
            colores.add(new Integer(c[i]));
        codigo(datos,cates,colores);
        amount=0;
    }
    public void setLista(Cursor datos, List<String> cates){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llcate);;
        //setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.removeAllViews();
        Integer lol;
        List<Integer> catnum = new ArrayList<Integer>();
        int size = cates.size();
        for (int i = 0; i < size+1; i++)
        {
            catnum.add(0);
        }
        String a;
        //int entre,comid,trans,impue,otros=0;
        if (datos.getCount() == 0){
            Toast.makeText(this,"No hay datos",Toast.LENGTH_LONG).show();
        }else{
            while (datos.moveToNext()){

                for (int i = 0; i < size; i++)
                {
                    a = cates.get(i);
                    if (datos.getString(datos.getColumnIndex("categoria")).equals(a)){
                        lol=catnum.get(i);
                        catnum.set(i, lol+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad")))));
                    }
                }
            }}
        for( int i = 0; i < size; i++ )
        {

            Button eliminar = new Button(this);
            eliminar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 70));
            eliminar.setText("    ■  "+cates.get(i));
            eliminar.setBackgroundResource(R.drawable.button2);
            eliminar.setTextColor(coriginales.get(i).intValue());
            eliminar.setTag(cates.get(i)+"&"+i);
            eliminar.setOnClickListener(Switch);
            TextView jaja= new TextView(this);
            jaja.setLayoutParams(new LinearLayout.LayoutParams(300, 10));
            linearLayout.addView(jaja);
            eliminar.setBackgroundColor(00000000);
            eliminar.setGravity(Gravity.LEFT);
            //eliminar.setAlpha(00);
            linearLayout.addView(eliminar);

        }
    }
    View.OnClickListener Switch =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String actual= v.getTag().toString();
            final String[] aux= actual.split("&");
            int remover= Integer.parseInt(aux[1]);
            String booleano="F";
            int aux2;
            final Spinner c3 = (Spinner) findViewById(R.id.spinner5);
            String c13 = c3.getSelectedItem().toString();
            if (c13=="Todo el tiempo"){
                c13="a";
            }else if(c13=="Último mes"){
                c13="b";
            }else if(c13=="Última semana"){
                c13="c";
            }else if(c13=="Hoy"){
                c13="d";
            }
            Integer bool= categoriaseleccionadas.indexOf(aux[0]);
            if (bool==-1){
                categoriaseleccionadas.add(aux[0]);
                colorescategoriaseleccionadas.add(coriginales.get(remover));
                if (amount==0){
                    colores.remove(remover);
                }else{
                    if ((remover-amount)<0){
                        colores.remove(remover);
                    }else{
                    colores.remove(remover-amount);}
                }
                amount=amount+1;
            }else{
                amount=amount-1;
                categoriaseleccionadas.remove(categoriaseleccionadas.indexOf(aux[0]));
                colorescategoriaseleccionadas.remove(coriginales.indexOf(remover)+1);
                colores.clear();
                for (int i = 0; i < c.length; i++) {
                    colores.add(new Integer(c[i]));
                }
                    for (int i=coriginales.size();i>0;i=i-1){
                        for (int a=0;a<colorescategoriaseleccionadas.size();a++){
                            if (coriginales.get(i-1)==colorescategoriaseleccionadas.get(a)){
                                colores.remove(coriginales.indexOf(i)+1);

                            }
                        }
                    }
            }
            if (0<categoriaseleccionadas.size()){
                datos2=databaseHandler.getTableMovPiechartSelection(categoriaseleccionadas,c13,"a");
                cates2 = databaseHandler.loadCatePiechartSelection(categoriaseleccionadas);
                codigo(datos2,cates2,colores);
            }else{
                datos = databaseHandler.getTableMov();
                cates = databaseHandler.loadCate();
                colores.clear();
                amount=0;
                categoriaseleccionadas.clear();
                colorescategoriaseleccionadas.clear();
                for (int i = 0; i < c.length; i++)
                    colores.add(new Integer(c[i]));
                codigo(datos,cates,colores);
            }


    }
}


;}


