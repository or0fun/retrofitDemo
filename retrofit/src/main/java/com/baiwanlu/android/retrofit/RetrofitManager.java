package com.baiwanlu.android.retrofit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lufei on 6/26/16.
 */
public class RetrofitManager {

    static boolean sLogEnable = false;

    static int sTimeout = 300;

    static Map<String, Retrofit> retrofitStringMap = new HashMap<>();

    public static void setLogEnable(boolean logEnable) {
        sLogEnable = logEnable;
    }

    public static void setTimeout(int timeout) {
        sTimeout = timeout;
    }

    public static Retrofit get(String baseUrl) {

        if (retrofitStringMap.containsKey(baseUrl)) {
            return retrofitStringMap.get(baseUrl);
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.connectTimeout(sTimeout, TimeUnit.SECONDS);
        if (sLogEnable) {
            httpClientBuilder.addInterceptor(logging);
        }

        Retrofit.Builder builder =  new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClientBuilder.build());


        Retrofit retrofit = builder.build();
        retrofitStringMap.put(baseUrl, retrofit);

        return retrofit;
    }
}
