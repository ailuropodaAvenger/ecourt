package com.cubex.ecourt;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fastaccess.datetimepicker.DatePickerFragmentDialog;
import com.fastaccess.datetimepicker.DateTimeBuilder;
import com.fastaccess.datetimepicker.callback.DatePickerCallback;
import com.fastaccess.datetimepicker.callback.TimePickerCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.maxDate;
import static android.R.attr.minDate;



public class ComplainDescription extends AppCompatActivity implements DatePickerCallback, TimePickerCallback {

    private TextView complainTime;



    Button pickTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(ComplainDescription.this, CrimePlaceInformation.class));
            }
        });

        complainTime = (TextView) findViewById(R.id.crime_time);
        pickTime = (Button) findViewById(R.id.btn_crime_time);






        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar minDate = Calendar.getInstance();
                minDate.set(2016, minDate.get(Calendar.MONTH), minDate.get(Calendar.DAY_OF_MONTH));
                Calendar maxDate = Calendar.getInstance();
                maxDate.set(2016, minDate.get(Calendar.MONTH) + 1, minDate.get(Calendar.DAY_OF_MONTH));
                Calendar currentDate = Calendar.getInstance();

                DatePickerFragmentDialog.newInstance(
                        DateTimeBuilder.get()
                                .withTime(true)
                                .with24Hours(false)
                                .withSelectedDate(currentDate.getTimeInMillis())
                                .withMinDate(minDate.getTimeInMillis())
                                .withMaxDate(maxDate.getTimeInMillis())
                                .withCurrentHour(12)
                                .withCurrentMinute(30))
                        .show(getSupportFragmentManager(), "DatePickerFragmentDialog");
            }
        });

    }

    @Override
    public void onDateSet(long date) {
        complainTime.setText(getDateOnly(date));
    }

    @Override
    public void onTimeSet(long timeOnly, long dateWithTime) {
      //  complainTime.setText(String.format("Full Date: %s\nTime Only: %s", getDateAndTime(dateWithTime), getTimeOnly(timeOnly)));
        complainTime.setText(String.format("%s", getDateAndTime(dateWithTime)));
    }

    public static String getDateOnly(long time) {
        return new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(time);
    }

    public static String getDateAndTime(long time) {
        SimpleDateFormat sample = new SimpleDateFormat("dd MMM yyyy, hh:mma", Locale.getDefault());
        return sample.format(new Date(time));
    }

    public static String getTimeOnly(long time) {
        SimpleDateFormat sample = new SimpleDateFormat("hh:mma", Locale.getDefault());
        return sample.format(time);
    }
}
