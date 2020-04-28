
package com.example.user.eatmeapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.example.user.eatmeapplication.listviewitems.ChartItem;
import com.example.user.eatmeapplication.listviewitems.PieChartItem;
import com.example.user.eatmeapplication.DemoBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of charts inside a ListView. IMPORTANT: provide a
 * specific height attribute for the chart inside your listview-item
 *
 * @author Philipp Jahoda
 */
public class HalfPieChart extends DemoBase {
    private double totalCalories=40;
    private double fatsPercent=50;
    private double proteinsPercent=10;
    private double carbsPercent=40;

    public HalfPieChart(double totalCalories, double fatsPercent, double proteinsPercent, double carbsPercent) {
        this.totalCalories = totalCalories;
        this.fatsPercent = fatsPercent;
        this.proteinsPercent = proteinsPercent;
        this.carbsPercent = carbsPercent;
    }

    public void createHalf( ListView lv,Context c) {
        ArrayList<ChartItem> list = new ArrayList<ChartItem>();
        list.add(new PieChartItem(generateDataPie(1),c,totalCalories)); //constuctur for pichartItem
        ChartDataAdapter cda = new ChartDataAdapter(c, list);
        lv.setAdapter(cda);
    }

    /** adapter that supports 3 different item types */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            return getItem(position).getItemType();
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie(int cnt) {
        ArrayList<PieEntry> values = new ArrayList<PieEntry>();
        values.add(new PieEntry((float)fatsPercent, CalloriesParts[0]));
        values.add(new PieEntry((float)proteinsPercent, CalloriesParts[1]));
        values.add(new PieEntry((float)carbsPercent, CalloriesParts[2]));

        PieDataSet dataSet = new PieDataSet(values, "Calories Parts");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        return data;
    }
}
