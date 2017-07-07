package ru.ticketeen.api;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    private static ApiHelper instance;
    private FnsApi api;

    private ApiHelper(String fnsApiBaseUrl) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(fnsApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        api = retrofit.create(FnsApi.class);
    }

    public static ApiHelper getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Need to call init() before getInstance()");
        }
        return instance;
    }

    public static void init(String baseFnsApiUrl) {
        if (instance != null) {
            throw new IllegalStateException("Call method init() once");
        }
        instance = new ApiHelper(baseFnsApiUrl);
    }
}
