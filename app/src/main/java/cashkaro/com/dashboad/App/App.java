package cashkaro.com.dashboad.App;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by yasar on 18/4/17.
 */

public class App extends Application {
    private static final String TAG = "App";
    //    private static final String url = "https://testingdatabase.pappaya.co.uk/";
//        private static final String url = "http://ed.think42labs.com/";
//    private static final String url = "http://192.168.1.70:8072/vms/fire_muster/";
    private static final String url = "http://192.168.1.70:8072/vms/device/";
    //    private static final String url = "http://192.168.0.101:8069/vms/fire_muster/";
    private static App app;
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor httpLoggingInterceptor;
    private static Interceptor provideOfflineCacheInterceptor;
    private static Interceptor provideCacheInterceptor;
    private static OkHttpClient okHttpClient;
    private static final String CACHE_CONTROL = "Cache-Control";
    private SharedPreferences preferences;

    private byte[] base64FIle;

    public byte[] getBase64FIle() {
        return base64FIle;
    }

    public void setBase64FIle(byte[] base64FIle) {
        this.base64FIle = base64FIle;
    }

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        provideCacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(2, TimeUnit.MINUTES)
                        .build();

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };

        provideOfflineCacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                if (!hasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);

            }
        };

        httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

            }
        });

        File cache_file = new File(getCacheDir(), "response");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache catche = new Cache(cache_file, cacheSize);

        okHttpClient = new OkHttpClient().newBuilder().addInterceptor(provideOfflineCacheInterceptor).addInterceptor(httpLoggingInterceptor).addNetworkInterceptor(provideCacheInterceptor).cache(catche).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

    public boolean hasNetwork() {
        return checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
