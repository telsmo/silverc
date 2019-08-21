package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("namexd");
        databaseHandler = new DatabaseHelper(this, name);
        setContentView(R.layout.analisispiechart);
        pieChart= (PieChart) findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setHoleColor(getResources().getColor(R.color.prim0));
        pieChart.setTransparentCircleRadius(60f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34F,"Bangladesh"));
        yValues.add(new PieEntry(23F,"USA"));
        yValues.add(new PieEntry(14F,"UK"));
        yValues.add(new PieEntry(35,"India"));
        yValues.add(new PieEntry(40,"Russia"));
        yValues.add(new PieEntry(23,"Japan"));

        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data= new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(getResources().getColor(R.color.prim0));

        pieChart.setData(data);

    }
}

