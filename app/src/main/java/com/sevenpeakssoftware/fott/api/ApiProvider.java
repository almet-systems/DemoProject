package com.sevenpeakssoftware.fott.api;

import com.sevenpeakssoftware.fott.api.gson.GsonConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by razir on 1/3/2017.
 */

public class ApiProvider {

    static ApiProvider apiProvider;
    private static final String MAIN_URL = "https://www.apphusetreach.no/application/40495/";
    IApiProvider api;

    public static ApiProvider getInstance() {
        if (apiProvider == null) {
            apiProvider = new ApiProvider();
        }
        return apiProvider;
    }


    public ApiProvider() {
        Retrofit retrofit = initRetrofit();
        api = retrofit.create(IApiProvider.class);
    }

    protected Retrofit initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonConfig.buildDefaultJson()))
                .baseUrl(MAIN_URL)
                .build();
        return retrofit;
    }


    public IApiProvider getApi() {
        return api;
    }
}
