package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class analisispiechart extends Activity {
    private DatabaseHelper databaseHandler;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analisispiechart);
        codigo();
    }
    /*
    public void actualizar(View view){
        codigo();
    }
    */
    private void codigo(){
        Integer lol;
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        Cursor datos = databaseHandler.getTableMov();
        List<String> cates = databaseHandler.loadCate();
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
/*
                if (datos.getString(datos.getColumnIndex("categoria")).equals("Entretenimiento")){
                    entre= entre+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                }else if (datos.getString(datos.getColumnIndex("categoria")).equals("Comida")){
                    comid= comid+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                } else if (datos.getString(datos.getColumnIndex("categoria")).equals("Transporte")){
                    trans= trans+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                }else if (datos.getString(datos.getColumnIndex("categoria")).equals("Impuestos")){
                    impue= impue +((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                }else if (datos.getString(datos.getColumnIndex("categoria")).equals("Otros")){
                    otros= otros+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                }
*/
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
        /*
        yValues.add(new PieEntry(entre,"Entretenimiento"));
        yValues.add(new PieEntry(comid,"Comida"));
        yValues.add(new PieEntry(impue,"Impuestos"));
        yValues.add(new PieEntry(otros,"Otros"));
        yValues.add(new PieEntry(trans,"Transporte"));
         */
        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(new int[] { getResources().getColor(R.color.black), getResources().getColor(R.color.blue),getResources().getColor(R.color.orange),getResources().getColor(R.color.red),getResources().getColor(R.color.dark_goldenrod),getResources().getColor(R.color.violet),getResources().getColor(R.color.deep_sky_blue),getResources().getColor(R.color.pink),getResources().getColor(R.color.green),getResources().getColor(R.color.greenYellow),getResources().getColor(R.color.fire_brick)});
        dataSet.setValueLineColor(getResources().getColor(R.color.prim1));
        PieData data= new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(getResources().getColor(R.color.prim0));

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llcate);;
        //setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for( int i = 0; i < size; i++ )
        {
            TextView textView = new TextView(this);
            textView.setText(cates.get(i)+" : $"+catnum.get(i));
            textView.setTextColor(dataSet.getColor(i));
            linearLayout.addView(textView);
        }

        pieChart.setData(data);

    }

}

