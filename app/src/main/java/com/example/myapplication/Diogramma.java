package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Diogramma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diogramma);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(10f,0));
        entries.add(new BarEntry(6f,1));
        entries.add(new BarEntry(5f,2));
        entries.add(new BarEntry(8f,3));
        entries.add(new BarEntry(4f,4));
        entries.add(new BarEntry(7f,5));

        BarDataSet dataset = new BarDataSet (entries,"");
        ArrayList<String> labels =  new ArrayList<String>();
        labels.add("");
        labels.add("Крепость Орешек   Гатчинский дворец");
        labels.add("");
        labels.add("Выборский замок  Саблинский памятник природы");
        labels.add("");
        labels.add("Покровская церковь   Крепость Корела");
        BarChart chart =new BarChart(this);
        setContentView(chart);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(labels,dataset);
        chart.setData(data);
        chart.setDescription("Диограмма(тыс.чел)");
    }
}