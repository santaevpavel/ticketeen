package ru.ticketeen.api;


import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.ticketeen.api.response.GetTicketResponse;
import ru.ticketeen.api.util.BasicAuthInterceptor;
import ru.ticketeen.api.util.IUserCredentialsProvider;

import static ru.ticketeen.api.FnsApi.FILE_TYPE_JSON;

public class ApiHelper {

    private static ApiHelper instance;
    private FnsApi api;

    public ApiHelper(String fnsApiBaseUrl, IUserCredentialsProvider userCredentialsProvider) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(userCredentialsProvider))
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

    public static void init(String baseFnsApiUrl, IUserCredentialsProvider userCredentialsProvider) {
        if (instance != null) {
            throw new IllegalStateException("Call method init() once");
        }
        instance = new ApiHelper(baseFnsApiUrl, userCredentialsProvider);
    }

    public GetTicketResponse getTickets() {
        return makeRequest(api.getTickets(0, FILE_TYPE_JSON));
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
