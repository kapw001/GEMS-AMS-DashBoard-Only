package cashkaro.com.dashboad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        String v = "2017-08-30 07:08";

        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format2.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = format2.parse(v);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "onCreate: " + date.toString());


        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, 0);

        Log.e(TAG, "onCreate: calendar " + c.getTime());

        DateFormat format4 = new SimpleDateFormat("h:mm a");
//        format4.setTimeZone(TimeZone.getTimeZone("UTC"));
        String finalDay = format4.format(c.getTime());

        Log.e(TAG, "onCreate: ori values   " + finalDay);


    }
}
