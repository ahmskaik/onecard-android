package com.nawasoft.oneapp.util;

import android.app.DatePickerDialog;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarUtil {

    private Context context;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat;

    private OnDateSelected onDateSelected;
    public CalendarUtil(Context context, OnDateSelected onDateSelected) {
        this.context = context;
        this.onDateSelected = onDateSelected;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    }

    public void showPicker() {
        new DatePickerDialog(
                context,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        onDateSelected.onDateSelected(simpleDateFormat.format(calendar.getTime()));
    };

    public interface OnDateSelected {
        void onDateSelected(String date);
    }

}
