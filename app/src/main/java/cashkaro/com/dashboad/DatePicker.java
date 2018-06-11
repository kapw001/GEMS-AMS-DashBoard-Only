package cashkaro.com.dashboad;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.rackspira.kristiawan.rackmonthpicker.RackMonthPicker;
import com.rackspira.kristiawan.rackmonthpicker.listener.DateMonthDialogListener;
import com.rackspira.kristiawan.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yasar on 26/8/17.
 */

public class DatePicker {

    public static void showDatePicker(final Context context, final CallBackDayPickerValues callBackDayPickerValues) {
        final int mYear, mMonth, mDay, mHour, mMinute;
        // Get Current Date
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        final DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
//

                        callBackDayPickerValues.showDateValues(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);


        datePickerDialog.show();

    }

    public static void showDatePicker(final Context context, final CallBackDayPickerValues callBackDayPickerValues, final String enddate) {
        final int mYear, mMonth, mDay, mHour, mMinute;
        // Get Current Date
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        final DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
//                        year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                        Date sD = Utils.convertStringToDate(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        Date eD = Utils.convertStringToDate(enddate);

                        if (eD.before(sD) || eD.equals(sD)) {

                            callBackDayPickerValues.showDateValues(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else {

                            Toast.makeText(context, "End date less then start date,please select another date", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, mYear, mMonth, mDay);


        datePickerDialog.show();

    }

    public static void showMonthPicker(final Context context, final CallBackDayPickerValues callBackDayPickerValues, final String enddate) {
        final RackMonthPicker rackMonthPicker = new RackMonthPicker(context);

        rackMonthPicker.setNegativeButton(new OnCancelMonthDialogListener() {
            @Override
            public void onCancel(AlertDialog alertDialog) {
                rackMonthPicker.dismiss();
            }
        });

        rackMonthPicker.setPositiveButton(new DateMonthDialogListener() {
            @Override
            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {

                Date sD = Utils.convertStringToDate(year + "-" + (month) + "-" + startDate);
                Date eD = Utils.convertStringToDate(enddate);

                if (eD.before(sD) || eD.equals(sD)) {

                    callBackDayPickerValues.showDateValues(year + "-" + (month) + "-" + startDate);
                    callBackDayPickerValues.showEndDateValues(year + "-" + (month) + "-" + endDate);

                    Log.e("Month Picker  ", "onDateMonth: " + year + "-" + (month) + "-" + startDate + " " + endDate + "  " + monthLabel);

                } else {

                    Toast.makeText(context, "End date less then start date", Toast.LENGTH_SHORT).show();
                }
                rackMonthPicker.dismiss();


            }
        });
//                        .setNegativeButton(new OnCancelMonthDialogListener() {
//                            @Override
//                            public void onCancel(AlertDialog dialog) {
//
//
//                            }
//                        })
        rackMonthPicker.show();
    }


    public static void showMonthPicker(final Context context, final CallBackDayPickerValues callBackDayPickerValues) {
        final RackMonthPicker rackMonthPicker = new RackMonthPicker(context);

        rackMonthPicker.setNegativeButton(new OnCancelMonthDialogListener() {
            @Override
            public void onCancel(AlertDialog alertDialog) {
                rackMonthPicker.dismiss();
            }
        });

        rackMonthPicker.setPositiveButton(new DateMonthDialogListener() {
            @Override
            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {


                callBackDayPickerValues.showDateValues(year + "-" + (month) + "-" + startDate);
                callBackDayPickerValues.showEndDateValues(year + "-" + (month) + "-" + endDate);

                Log.e("Month Picker  ", "onDateMonth: " + year + "-" + (month) + "-" + startDate + " " + endDate + "  " + monthLabel);

                rackMonthPicker.dismiss();


            }
        });
//                        .setNegativeButton(new OnCancelMonthDialogListener() {
//                            @Override
//                            public void onCancel(AlertDialog dialog) {
//
//
//                            }
//                        })
        rackMonthPicker.show();
    }


    public static void showDatePicker(final Context context, final CallBackDayPickerValues callBackDayPickerValues, Date date, final String enddate) {
        int mYear, mMonth, mDay, mHour, mMinute;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        c.setTime(date);

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
//                        year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                        Date sD = Utils.convertStringToDate(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        Date eD = Utils.convertStringToDate(enddate);

                        if (eD.before(sD) || eD.equals(sD)) {

                            callBackDayPickerValues.showDateValues(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else {

                            Toast.makeText(context, "End date less then start date", Toast.LENGTH_SHORT).show();
                        }

//                        callBackDayPickerValues.showDateValues(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);


        datePickerDialog.show();

    }

    interface CallBackDayPickerValues {

        void showDateValues(String datepicker);

        void showEndDateValues(String datepicker);
    }
}
