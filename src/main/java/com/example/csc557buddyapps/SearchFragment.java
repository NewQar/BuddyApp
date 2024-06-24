package com.example.csc557buddyapps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class SearchFragment extends Fragment {

    private DatePicker datePickerFrom;
    private DatePicker datePickerTo;
    private TextView resultDays;
    private TextView resultWeeksDays;
    private TextView resultMonthsDays;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        datePickerFrom = view.findViewById(R.id.datePickerFrom);
        datePickerTo = view.findViewById(R.id.datePickerTo);
        resultDays = view.findViewById(R.id.resultDays);
        resultWeeksDays = view.findViewById(R.id.resultWeeksDays);
        resultMonthsDays = view.findViewById(R.id.resultMonthsDays);

        view.findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDateDifference();
            }
        });

        return view;
    }

    private void calculateDateDifference() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(datePickerFrom.getYear(), datePickerFrom.getMonth(), datePickerFrom.getDayOfMonth());

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(datePickerTo.getYear(), datePickerTo.getMonth(), datePickerTo.getDayOfMonth());

        long startMillis = startCalendar.getTimeInMillis();
        long endMillis = endCalendar.getTimeInMillis();
        long totalDays = TimeUnit.MILLISECONDS.toDays(Math.abs(endMillis - startMillis));

        resultDays.setText(totalDays + " days");

        long weeks = totalDays / 7;
        long days = totalDays % 7;
        resultWeeksDays.setText(weeks + " Weeks " + days + " Days");

        long months = totalDays / 30; // Approximation
        days = totalDays % 30;
        resultMonthsDays.setText(months + " Months " + days + " Days");
    }
}
