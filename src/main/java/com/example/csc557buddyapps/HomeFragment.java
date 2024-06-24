package com.example.csc557buddyapps;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    BarChart barChart;
    PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        barChart = view.findViewById(R.id.bar_chart);
        pieChart = view.findViewById(R.id.pie_chart);

        initBarChart();
        initPieChart();

        return view;
    }

    private void initBarChart() {
        // You can initialize and populate the BarChart here
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        // Replace this block with your actual data retrieval logic
        DatabaseHandler dbHandler = new DatabaseHandler(requireContext());  // Assuming you're in a Fragment

        int maleCount = dbHandler.getGenderCount("Male");
        int femaleCount = dbHandler.getGenderCount("Female");

        // Add entries in the correct order
        barEntries.add(new BarEntry(0, maleCount));   // Use 0 for Male
        barEntries.add(new BarEntry(1, femaleCount)); // Use 1 for Female

        dbHandler.close();

        BarDataSet barDataSet = new BarDataSet(barEntries, "Gender");
        // Set custom colors for each bar entry
        int[] colors = new int[]{Color.BLUE, Color.rgb(255, 182, 193)};
        barDataSet.setColors(colors);

        // Set labels for each bar entry
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Male");
        labels.add("Female");

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f); // Set a bit of space between the bars

        barChart.setData(barData);
        barChart.animateY(3000);
        barChart.getDescription().setEnabled(false);

        // Set labels at the bottom of each bar
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);

        // Set value text size and format
        barDataSet.setValueTextSize(12f);
        barDataSet.setValueFormatter(new DefaultValueFormatter(0));

    }


    private void initPieChart() {
        // You can initialize and populate the PieChart here
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        // Replace this block with your actual data retrieval logic
        DatabaseHandler dbHandler = new DatabaseHandler(requireContext());  // Assuming you're in a Fragment
        int[] birthMonthCount = dbHandler.getBirthMonthCount();
        String[] monthLabels = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        for (int i = 0; i < birthMonthCount.length; i++) {
            int count = birthMonthCount[i];
            if (count > 0) {
                // Format the month value to match the format in monthLabels array
                String formattedMonth = String.format("%02d", i + 1);
                pieEntries.add(new PieEntry(count, monthLabels[Integer.parseInt(formattedMonth) - 1]));
            }
        }

        dbHandler.close();

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Birth Month");
        // Set custom colors for each pie entry
        int[] colors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.GRAY, Color.DKGRAY, Color.LTGRAY, Color.rgb(255, 165, 0), Color.rgb(255, 0, 255), Color.rgb(0, 255, 255)};
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.animateXY(3000, 3000);

        pieChart.getDescription().setEnabled(false);

        pieDataSet.setValueTextSize(12f);
        pieDataSet.setValueFormatter(new DefaultValueFormatter(0));
    }



}
