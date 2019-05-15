package com.ripple.effects.fb.java.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ripple.effects.fb.java.BuildConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        return createService(serviceClass, baseUrl, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, HashMap<String, String> headers) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        setupHttpClient(httpClient);
        addInterceptor(httpClient, headers);
        Retrofit.Builder builder = setApiBaseUrl(baseUrl);
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create(getGsonBuilder()));
        addLogInterceptor(httpClient);
        return builder.client(httpClient.build()).build().create(serviceClass);
    }

    private static void setupHttpClient(OkHttpClient.Builder httpClient) {
        httpClient.connectTimeout(NetworkDefine.connectionTimeout, TimeUnit.SECONDS);
        httpClient.readTimeout(NetworkDefine.readTimeout, TimeUnit.MINUTES);
        httpClient.writeTimeout(NetworkDefine.writeTimeout, TimeUnit.MINUTES);
        httpClient.retryOnConnectionFailure(false);
    }

    private static Gson getGsonBuilder() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    private static void addInterceptor(OkHttpClient.Builder httpClient, final HashMap<String, String> headers) {
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain)
                    throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                if (headers != null && headers.size() > 0) {
                    for (String key : headers.keySet()) {
                        if (headers.get(key) != null) {
                            requestBuilder.addHeader(key, headers.get(key));
                        }
                    }
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
    }

    private static void addLogInterceptor(OkHttpClient.Builder httpClient) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        httpClient.addInterceptor(interceptor);
    }

    private static Retrofit.Builder setApiBaseUrl(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl);
    }
}
