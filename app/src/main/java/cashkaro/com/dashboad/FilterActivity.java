package cashkaro.com.dashboad;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinner;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.rackspira.kristiawan.rackmonthpicker.RackMonthPicker;
import com.rackspira.kristiawan.rackmonthpicker.listener.DateMonthDialogListener;
import com.rackspira.kristiawan.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import cashkaro.com.dashboad.adapter.MultiSelectionSpinner;
import cashkaro.com.dashboad.model.PassValuesTwoActivity;
import cashkaro.com.dashboad.model.SchoolList;
import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;

public class FilterActivity extends AppCompatActivity {


    private List<String> list;

    private MultiSelectionSpinner spinner;

    private ImageView back, accept;

    private TextView startdate, enddate;

    private RadioGroup radioGroup;

    private RadioButton radioButton;

    private String startdateTxt, enddateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if the version of Android is Lollipop or higher
        if (Build.VERSION.SDK_INT >= 21) {

            // Set the status bar to dark-semi-transparentish
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        }

        setContentView(R.layout.activity_filter);

        radioGroup = (RadioGroup) findViewById(R.id.rdogrp);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

//                radioButton = (RadioButton) findViewById(i);

                setDate(startdateTxt, enddateTxt);

            }
        });

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.filtercustomactionbar);//
        View view = getSupportActionBar().getCustomView();

        startdate = (TextView) findViewById(R.id.startdate);
        enddate = (TextView) findViewById(R.id.enddate);

        setDate(Utils.getStartDateOfMonth(new Date()), Utils.addDays(Utils.getStartDateOfMonth(new Date())));

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == R.id.month) {

                    DatePicker.showMonthPicker(FilterActivity.this, new DatePicker.CallBackDayPickerValues() {
                        @Override
                        public void showDateValues(String datepicker) {
                            startdateTxt = datepicker;
                            enddateTxt = Utils.addDays(datepicker);
                            if (selectedId == R.id.month) {
                                String d = Utils.convertDateToString(Utils.convertStringToDate(datepicker));
                                String ed = Utils.convertDateToString(Utils.convertStringToDate(Utils.addDays(datepicker)));
                                String v = Utils.getMonthByName(d) + "," + Utils.getDaysByNumber(d);

                                startdate.setText(v);

                                String v1 = Utils.getMonthByName(ed) + "," + Utils.getDaysByNumber(ed);

                                enddate.setText(v1);
                            } else {
                                String d = Utils.convertDateToString(Utils.convertStringToDate(datepicker));

//                            String v = Utils.getMonthByName(d) + "," + Utils.getDaysByNumber(d);

                                startdate.setText(d);

                                String v1 = Utils.addDays(d);

                                enddate.setText(v1);

                            }
                        }

                        @Override
                        public void showEndDateValues(String datepicker) {

                        }
                    });


                } else {


                    DatePicker.showDatePicker(FilterActivity.this, new DatePicker.CallBackDayPickerValues() {
                        @Override
                        public void showDateValues(String datepicker) {

                            int selectedId = radioGroup.getCheckedRadioButtonId();
                            startdateTxt = datepicker;
                            enddateTxt = Utils.addDays(datepicker);
                            if (selectedId == R.id.month) {
                                String d = Utils.convertDateToString(Utils.convertStringToDate(datepicker));
                                String ed = Utils.convertDateToString(Utils.convertStringToDate(Utils.addDays(datepicker)));

                                String v = Utils.getMonthByName(d) + "," + Utils.getDaysByNumber(d);

                                startdate.setText(v);

                                String v1 = Utils.getMonthByName(ed) + "," + Utils.getDaysByNumber(ed);

                                enddate.setText(v1);
                            } else {
                                String d = Utils.convertDateToString(Utils.convertStringToDate(datepicker));

//                            String v = Utils.getMonthByName(d) + "," + Utils.getDaysByNumber(d);

                                startdate.setText(d);

                                String v1 = Utils.addDays(d);

                                enddate.setText(v1);

                            }

                        }

                        @Override
                        public void showEndDateValues(String datepicker) {

                        }
                    });

                }


            }
        });

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Tag", "onClick: " + enddateTxt);
                final int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == R.id.month) {

                    DatePicker.showMonthPicker(FilterActivity.this, new DatePicker.CallBackDayPickerValues() {
                        @Override
                        public void showDateValues(String datepicker) {


                        }

                        @Override
                        public void showEndDateValues(String datepicker) {
                            enddateTxt = datepicker;
                            if (selectedId == R.id.month) {
                                String d = Utils.convertDateToString(Utils.convertStringToDate(datepicker));
                                String v = Utils.getMonthByName(d) + "," + Utils.getDaysByNumber(d);
                                enddate.setText(v);
                            } else {
                                String d = Utils.convertDateToString(Utils.convertStringToDate(datepicker));
                                enddate.setText(d);
                            }
                        }
                    }, startdateTxt);


                } else {
                    DatePicker.showDatePicker(FilterActivity.this, new DatePicker.CallBackDayPickerValues() {
                        @Override
                        public void showDateValues(String datepicker) {


                            enddateTxt = datepicker;
                            if (selectedId == R.id.month) {
                                String d = Utils.convertDateToString(Utils.convertStringToDate(datepicker));
                                String v = Utils.getMonthByName(d) + "," + Utils.getDaysByNumber(d);
                                enddate.setText(v);
                            } else {
                                String d = Utils.convertDateToString(Utils.convertStringToDate(datepicker));
                                enddate.setText(d);
                            }
                        }

                        @Override
                        public void showEndDateValues(String datepicker) {

                        }
                    }, Utils.convertStringToDate(enddateTxt), startdateTxt);
                }
            }
        });

        back = (ImageView) view.findViewById(R.id.back);
        accept = (ImageView) view.findViewById(R.id.accept);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(3, intent);
                finish();
                overridePendingTransition(R.animator.left_to_right, R.animator.right_to_left);
//                FilterActivity.super.onBackPressed();
//                Toast.makeText(FilterActivity.this, "Back", Toast.LENGTH_SHORT).show();
            }
        });


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(FilterActivity.this, "Accept", Toast.LENGTH_SHORT).show();


                PassValuesTwoActivity passValuesTwoActivity = new PassValuesTwoActivity();

                passValuesTwoActivity.setSchoolListList(spinner.getSelectedStrings());
                passValuesTwoActivity.setMonthorday(radioGroup.getCheckedRadioButtonId());
                passValuesTwoActivity.setStartDate(startdateTxt);
                passValuesTwoActivity.setEnddate(enddateTxt);
                passValuesTwoActivity.setSchoolselectedcount(spinner.getSelectedStrings().size() + " Schools Selected");

                Intent intent = new Intent();

                intent.putExtra("passvalues", passValuesTwoActivity);

//                intent.putStringArrayListExtra("school_list", spinner.getSelectedList());
//                intent.putExtra("monthorday", radioGroup.getCheckedRadioButtonId());
//                intent.putExtra("startdate", startdateTxt);
//                intent.putExtra("enddate", enddateTxt);

                setResult(2, intent);
                finish();
                overridePendingTransition(R.animator.left_to_right, R.animator.right_to_left);


            }
        });


        list = new ArrayList<>();
        list.add("School I");
        list.add("School II");
        list.add("School III");
        list.add("School IV");
        list.add("School V");


        PassValuesTwoActivity passValuesTwoActivity = (PassValuesTwoActivity) getIntent().getSerializableExtra("passvalues");


        // Multi spinner
        spinner = (MultiSelectionSpinner) findViewById(R.id.mySpinner1);
        spinner.setItems(list);
        if (passValuesTwoActivity != null) {

            List<SchoolList> schoolLists = passValuesTwoActivity.getListofSchools();

            List<String> stringList = new ArrayList<>();

            for (int i = 0; i < schoolLists.size(); i++) {
                stringList.add(schoolLists.get(i).getName());
            }


            List<SchoolList> selectedList = passValuesTwoActivity.getSchoolSelectedList();

            if (selectedList.size() > 0) {

                List<String> stringList1 = new ArrayList<>();

                for (int i = 0; i < selectedList.size(); i++) {
                    stringList1.add(selectedList.get(i).getName());
                }

//            List<String> selectionList = passValuesTwoActivity.getSchoolListList();
                spinner.setItems(stringList);
                spinner.setSelection(stringList1);
            } else {
                spinner.setItems(stringList);
                spinner.setSelection(stringList);
            }
            setDate(Utils.convertDateToString(Utils.convertStringToDate(passValuesTwoActivity.getStartDate())), Utils.convertDateToString(Utils.convertStringToDate(passValuesTwoActivity.getEnddate())));
        }


    }

    private void setDate(String dateValues, String endDateValue) {
        startdateTxt = dateValues;
        enddateTxt = endDateValue;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.month) {
            String d = Utils.convertDateToString(Utils.convertStringToDate(dateValues));
            String ed = Utils.convertDateToString(Utils.convertStringToDate(Utils.getEndDateOfMonth(Utils.convertStringToDate(endDateValue))));

            String v = Utils.getMonthByName(d) + "," + Utils.getDaysByNumber(d);

            startdate.setText(v);

            String v1 = Utils.getMonthByName(ed) + "," + Utils.getDaysByNumber(ed);

            enddate.setText(v1);
        } else {


            String d = Utils.convertDateToString(Utils.convertStringToDate(dateValues));

//                            String v = Utils.getMonthByName(d) + "," + Utils.getDaysByNumber(d);

            startdate.setText(d);

            String v1 = endDateValue;

            enddate.setText(v1);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.animator.left_to_right, R.animator.right_to_left);
        super.onBackPressed();
    }
}
