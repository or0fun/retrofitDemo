package com.baiwanlu.android.retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    static Interceptor commonQueryParameterInterceptor;

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
        if (null != commonQueryParameterInterceptor) {
            httpClientBuilder.addInterceptor(commonQueryParameterInterceptor);
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

    /**
     * 设置公共参数
     */
    private static void addQueryParameterInterceptor(final Map<String, String> params) {
        commonQueryParameterInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                HttpUrl.Builder modifiedUrlBuilder = originalRequest.url().newBuilder();

                for(Map.Entry<String, String> entry : params.entrySet()){
                    modifiedUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
                request = originalRequest.newBuilder().url(modifiedUrlBuilder.build()).build();
                return chain.proceed(request);
            }
        };
    }
}
