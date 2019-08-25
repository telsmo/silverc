package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class analisispiechart extends Activity {
    private DatabaseHelper databaseHandler;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analisispiechart);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        Cursor datos = databaseHandler.getTableMov();
        int entre=0;
        int comid=0;
        int trans=0;
        int impue=0;
        int otros=0;
        if (datos.getCount() == 0){
            Toast.makeText(this,"No hay datos",Toast.LENGTH_LONG).show();
        }else{
            while (datos.moveToNext()){
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

            }}
        pieChart= (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setHoleColor(getResources().getColor(R.color.prim0));
        pieChart.setTransparentCircleRadius(60f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(entre,"Entretenimiento"));
        yValues.add(new PieEntry(comid,"Comida"));
        yValues.add(new PieEntry(impue,"Impuestos"));
        yValues.add(new PieEntry(otros,"Otros"));
        yValues.add(new PieEntry(trans,"Transporte"));
        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setValueLineColor(getResources().getColor(R.color.prim1));
        PieData data= new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(getResources().getColor(R.color.prim0));


        pieChart.setData(data);

    }
    public void actualizar(View view){
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        Cursor datos = databaseHandler.getTableMov();
        int entre=0;
        int comid=0;
        int trans=0;
        int impue=0;
        int otros=0;
        if (datos.getCount() == 0){
            Toast.makeText(this,"No hay datos",Toast.LENGTH_LONG).show();
        }else{
            while (datos.moveToNext()){
                if (datos.getString(datos.getColumnIndex("categoria")).equals("Entretenimiento")){
                    entre= entre+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                }else {
                    if (datos.getString(datos.getColumnIndex("categoria")).equals("Comida")){
                        comid= comid+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                }else {if (datos.getString(datos.getColumnIndex("categoria")).equals("Transporte")){
                    trans= trans+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                }else {if (datos.getString(datos.getColumnIndex("categoria")).equals("Impuestos")){
                    impue= impue +((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));
                }else {if (datos.getString(datos.getColumnIndex("categoria")).equals("Otros")){
                    otros= otros+((datos.getInt(datos.getColumnIndex("monto")))*(datos.getInt(datos.getColumnIndex("cantidad"))));}
                }}}}}

            }
        pieChart= (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setHoleColor(getResources().getColor(R.color.prim0));
        pieChart.setTransparentCircleRadius(60f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(entre,"Entretenimiento"));
        yValues.add(new PieEntry(comid,"Comida"));
        yValues.add(new PieEntry(impue,"Impuestos"));
        yValues.add(new PieEntry(otros,"Otros"));
        yValues.add(new PieEntry(trans,"Transporte"));
        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data= new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(getResources().getColor(R.color.prim0));
        pieChart.setData(data);
    }
}

