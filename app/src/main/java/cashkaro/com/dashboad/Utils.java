package cashkaro.com.dashboad;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;


/**
 * Created by yasar on 26/8/17.
 */

public class Utils {

    public static String json = "{\n" +
            "    \"jsonrpc\": \"2.0\",\n" +
            "    \"id\": null,\n" +
            "    \"result\": {\n" +
            "        \"todate\": \"2017-08-30\",\n" +
            "        \"fromdate\": \"2017-07-31\",\n" +
            "        \"university_id\": [\n" +
            "            1,\n" +
            "            2,\n" +
            "            3,\n" +
            "            4,\n" +
            "            5\n" +
            "        ],\n" +
            "        \"visitor_list\": [\n" +
            "            {\n" +
            "                \"data_set\": [\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-28 01:08\",\n" +
            "                        \"check_out\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-30 11:08\",\n" +
            "                        \"check_out\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-29 07:08\",\n" +
            "                        \"check_out\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-30 07:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-29 12:08\",\n" +
            "                        \"check_out\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-30 09:08\",\n" +
            "                        \"check_out\": \"2017-08-30 09:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-19 03:08\",\n" +
            "                        \"check_out\": \"2017-08-21 11:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-22 05:08\",\n" +
            "                        \"check_out\": \"2017-08-22 09:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-23 10:08\",\n" +
            "                        \"check_out\": \"2017-08-28 08:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-28 08:08\",\n" +
            "                        \"check_out\": \"2017-08-28 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-21 11:08\",\n" +
            "                        \"check_out\": \"2017-08-22 05:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-07-31 06:07\",\n" +
            "                        \"check_out\": \"2017-07-31 06:07\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-07-31 06:07\",\n" +
            "                        \"check_out\": \"2017-08-04 05:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-17 04:08\",\n" +
            "                        \"check_out\": \"2017-08-17 04:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 09:08\",\n" +
            "                        \"check_out\": \"2017-08-18 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-30 10:08\",\n" +
            "                        \"check_out\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-30 10:08\",\n" +
            "                        \"check_out\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-23 10:08\",\n" +
            "                        \"check_out\": \"2017-08-23 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-23 10:08\",\n" +
            "                        \"check_out\": \"2017-08-23 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-19 05:08\",\n" +
            "                        \"check_out\": \"2017-08-19 05:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-19 03:08\",\n" +
            "                        \"check_out\": \"2017-08-21 11:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-22 05:08\",\n" +
            "                        \"check_out\": \"2017-08-22 09:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-09 08:08\",\n" +
            "                        \"check_out\": \"2017-08-17 05:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-17 05:08\",\n" +
            "                        \"check_out\": \"2017-08-17 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 09:08\",\n" +
            "                        \"check_out\": \"2017-08-18 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 02:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 11:08\",\n" +
            "                        \"check_out\": \"2017-08-18 02:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-09 06:08\",\n" +
            "                        \"check_out\": \"2017-08-09 08:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-21 11:08\",\n" +
            "                        \"check_out\": \"2017-08-22 05:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-09 08:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-01 10:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-01 10:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-19 05:08\",\n" +
            "                        \"check_out\": \"2017-08-19 05:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 02:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-17 05:08\",\n" +
            "                        \"check_out\": \"2017-08-17 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-30 11:08\",\n" +
            "                        \"check_out\": \"2017-08-30 11:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-28 10:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"id\": 1\n" +
            "            },\n" +
            "            {\n" +
            "                \"data_set\": [\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-19 07:08\",\n" +
            "                        \"check_out\": \"2017-08-19 09:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-22 09:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-19 07:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-19 07:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-28 10:08\",\n" +
            "                        \"check_out\": \"2017-08-28 01:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-22 09:08\",\n" +
            "                        \"check_out\": \"2017-08-23 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-19 07:08\",\n" +
            "                        \"check_out\": \"2017-08-21 11:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-19 07:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-19 07:08\",\n" +
            "                        \"check_out\": \"2017-08-21 11:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-19 07:08\",\n" +
            "                        \"check_out\": \"2017-08-19 09:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-23 06:08\",\n" +
            "                        \"check_out\": \"2017-08-23 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-23 10:08\",\n" +
            "                        \"check_out\": \"2017-08-23 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-23 10:08\",\n" +
            "                        \"check_out\": \"2017-08-23 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-23 10:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"id\": 2\n" +
            "            },\n" +
            "            {\n" +
            "                \"data_set\": [\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-29 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"dbs\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-28 10:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"id\": 3\n" +
            "            },\n" +
            "            {\n" +
            "                \"data_set\": [\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-30 11:08\",\n" +
            "                        \"check_out\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-19 03:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-19 03:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-18 06:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-19 05:08\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"visitor_type\": \"normal\",\n" +
            "                        \"check_in\": \"2017-08-18 06:08\",\n" +
            "                        \"check_out\": \"2017-08-30 12:08\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"id\": 4\n" +
            "            },\n" +
            "            {\n" +
            "                \"data_set\": [],\n" +
            "                \"id\": 5\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";


    private static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    public static String getMonthByName(String date) {
        Date dt1 = null;
        try {
            dt1 = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("MMM");
        String finalDay = format2.format(dt1);
        return finalDay;
    }

    public static String getMonthByFullName(String date) {
        Date dt1 = null;
        try {
            dt1 = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("MMMM");
        String finalDay = format2.format(dt1);
        return finalDay;
    }

    public static String getDayByFullName(String date) {
        Date dt1 = null;
        try {
            dt1 = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("EEEE");
        String finalDay = format2.format(dt1);
        return finalDay;
    }

    public static String getHours(String date) {
        Date dt1 = null;
        try {
            dt1 = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("hh:mm a");
        String finalDay = format2.format(dt1);
        return finalDay;
    }


    public static String getHoursU(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, 0);
        DateFormat format4 = new SimpleDateFormat("h:mm a");
//        format4.setTimeZone(TimeZone.getTimeZone("UTC"));
        String finalDay = format4.format(c.getTime());

        return finalDay;

//        SimpleDateFormat format2 = new SimpleDateFormat("hh:mm a");
//        Calendar cal = Calendar.getInstance();
//
//        TimeZone tz = TimeZone.getTimeZone("GMT");
//
//        cal.setTimeZone(tz);
//        cal.setTime(date);
//        String finalDay = format2.format(cal.getTime());
//        return finalDay;
    }

    public static String getHours1(String date) {
        Date dt1 = null;
        Date _24HourDt = null;
        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
        try {
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dt1 = format2.parse(date);
            _24HourDt = _24HourSDF.parse(convertDateToString(dt1));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return _12HourSDF.format(_24HourDt);
    }


    public static String getDaysByNumber(String date) {
        Date dt1 = null;
        try {
            dt1 = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("dd");
        String finalDay = format2.format(dt1);
        return finalDay;
    }

    public static String getDaysByName(String date) {

        Date dt1 = null;
        try {
            dt1 = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("EEE");
        String finalDay = format2.format(dt1);
        return finalDay;
    }

    public static Date convertStringToDate(String s) {
        String str_date = s;
        Date date = null;
        try {
            date = format1.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date convertStringToDateN(String s) {
        String str_date = s;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            date = sdf.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date localToGMT() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gmt = new Date(sdf.format(date));
        return gmt;
    }

    public static Date convertStringToDateWithTime(String s) {

        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format2.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = format2.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
//
//        String str_date = s;
//        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
////        format2.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date date = null;
//        try {
//            date = format2.parse(str_date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        Log.e("Test", "comming date string: " + s + "    converted date string  " + date.toString() + " cal  ");
//        return date;

//        Log.e("Test", "comming date string: " + s + "    converted date string  " + date.toString() + " cal  " );
//
//        return date;
    }


    public static String getStartDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();   // this takes current date
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        String s = format1.format(c.getTime());
        return s;
    }

    public static String getEndDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();   // this takes current date
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String s = format1.format(c.getTime());
        return s;
    }


    public static String convertDateToString(Date date) {
        String s = format1.format(date);
        return s;
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static long putdays(String startdate, String enddate) throws ParseException {
        String str_date = startdate;
        String end_date = enddate;
        Date s;
        Date e;
        s = format1.parse(str_date);
        e = format1.parse(end_date);
        long diff = e.getTime() - s.getTime();
        long leavedays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        return leavedays;
    }

    public static String addDays(String d) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format1.parse(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        c.add(Calendar.DATE, 31);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        return format1.format(c.getTime());
    }

    public static String minusDays(String d) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format1.parse(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        c.add(Calendar.DATE, 31);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
//        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -30);
        return format1.format(c.getTime());
    }


    private static Calendar stringToCalendar(String string) throws ParseException {
        Date date = format1.parse(string);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static List<String> getMonthsFromTwoDatesS(String startdate, String enddate) {

        List<String> l = new ArrayList<>();

        Calendar startDate = null;
        Calendar endDate = null;
        try {
            startDate = stringToCalendar(startdate);
            endDate = stringToCalendar(enddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        l.add(getMonthByName(convertDateToString(startDate.getTime())));
        while (startDate.before(endDate)) {
            System.out.println(format1.format(startDate.getTime()));
            l.add(getMonthByName(convertDateToString(startDate.getTime())));
            startDate.add(Calendar.MONTH, 1);

            Log.e(TAG, "getMonthsFromTwoDatesS: " + getMonthByName(convertDateToString(startDate.getTime())));
        }


        return l;

    }

    public static String getMonthByNameNew(String date) {
        Date dt1 = null;
        final String NEW_FORMAT = "MMM, YY";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(NEW_FORMAT);
            dt1 = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("MMM");
        String finalDay = format2.format(dt1);
        return finalDay;
    }


    public static List<String> getMonthsFromTwoDates(Date startdate, Date enddate) {

        List<String> l = new ArrayList<>();

        Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();

        beginCalendar.setTime(startdate);
        finishCalendar.setTime(enddate);

        while (beginCalendar.before(finishCalendar)) {
//            // add one month to date per loop
//            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            String date = format1.format(beginCalendar.getTime()).toUpperCase();
            System.out.println(date);

            l.add(getMonthByName(date));

            beginCalendar.add(Calendar.MONTH, 1);
        }

        return l;

    }

    static int monthsBetween(Date a, Date b) {
        Calendar cal = Calendar.getInstance();
        if (a.before(b)) {
            cal.setTime(a);
        } else {
            cal.setTime(b);
            b = a;
        }
        int c = 0;
        while (cal.getTime().before(b)) {
            cal.add(Calendar.MONTH, 1);
            c++;
        }
        return c - 1;
    }

}
