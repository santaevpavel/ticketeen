package ru.ticketeen.api;


import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.ticketeen.api.response.GetReceiptsResponse;
import ru.ticketeen.api.util.BasicAuthInterceptor;

import static ru.ticketeen.api.FnsApi.FILE_TYPE_JSON;

public class ApiHelper {

    private static ApiHelper instance;
    private FnsApi api;

    private ApiHelper(String fnsApiBaseUrl, String login, String password) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(login, password))
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

    public static void init(String baseFnsApiUrl, String login, String password) {
        if (instance != null) {
            throw new IllegalStateException("Call method init() once");
        }
        instance = new ApiHelper(baseFnsApiUrl, login, password);
    }

    public GetReceiptsResponse getReceipts() {
        return makeRequest(api.getReceipts(0, FILE_TYPE_JSON));
    }

    private <T> T makeRequest(Call<T> httpCall) {
        Response<T> response = null;
        try {
            response = httpCall.execute();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throwApiError("Timeout");
        } catch (IOException e) {
            e.printStackTrace();
            throwApiError("Request failed");
        }
        if (response == null || !response.isSuccessful()) {
            throwApiError(response.message());
        }
        return response.body();
    }

    private void throwApiError(String msg) {
        throw new RuntimeException(msg);
    }

    public interface ApiRequesterFunction<T, R> {
        T apply(ApiHelper requester, R var);
    }

}
