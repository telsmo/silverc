package com.example.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class analisispiechart extends Activity {
    int amount=0;
    String query="";
    String query2="";
    Cursor datos;
    Cursor datos2;
    int[] c;
    ArrayList<Integer>colores= new ArrayList<Integer>();
    ArrayList<Integer>coriginales = new ArrayList<Integer>();
    List<String> cates;
    private DatabaseHelper databaseHandler;
    PieChart pieChart;
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
        pieChart.setHoleColor(getResources().getColor(R.color.prim0));
        pieChart.setTransparentCircleRadius(60f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {

                yValues.add(new PieEntry(catnum.get(i),""));

        }
        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);
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
        datos = databaseHandler.getTableMov();
        cates = databaseHandler.loadCate();
        for (int i = 0; i < c.length; i++) {
            colores.clear();
        }
        for (int i = 0; i < c.length; i++)
            colores.add(new Integer(c[i]));
        codigo(datos,cates,colores);
        query="";
        query2="";
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
            eliminar.setText(cates.get(i)+" : $"+catnum.get(i));
            eliminar.setBackgroundResource(R.drawable.button2);
            eliminar.setTextColor(coriginales.get(i).intValue());
            eliminar.setTag(cates.get(i)+"&"+i);
            eliminar.setOnClickListener(Switch);
            TextView jaja= new TextView(this);
            jaja.setLayoutParams(new LinearLayout.LayoutParams(300, 10));
            linearLayout.addView(jaja);
            linearLayout.addView(eliminar);

        }
    }
    View.OnClickListener Switch =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String actual= v.getTag().toString();
            final String[] aux= actual.split("&");
            int remover= Integer.parseInt(aux[1]);
            colores.remove(remover-amount);
            if (amount==0){
            datos2=databaseHandler.getTableMovPiechartSelection(query="categoria='"+aux[0]+"'");
            query2="nombre_cate='"+aux[0]+"'";
            amount=1;
            cates = databaseHandler.loadCatePiechartSelection(query2);
                codigo(datos2,cates,colores);
            }else{
                amount=amount+1;
                datos2=databaseHandler.getTableMovPiechartSelection(query=query+" AND NOT categoria='"+aux[0]+"'");
                query2=query2+" AND NOT nombre_cate='"+aux[0]+"'";
                cates = databaseHandler.loadCatePiechartSelection(query2);
                codigo(datos2,cates,colores);
            }

        }
    };
}

