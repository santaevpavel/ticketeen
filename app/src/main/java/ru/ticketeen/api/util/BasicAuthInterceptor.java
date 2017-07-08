package ru.ticketeen.api.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    private IUserCredentialsProvider provider;

    public BasicAuthInterceptor(IUserCredentialsProvider provider) {
        this.provider = provider;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", provider.getUserCredentials()).build();
        return chain.proceed(authenticatedRequest);
    }

}