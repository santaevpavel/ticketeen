package ru.ticketeen.api.util;


import okhttp3.Credentials;

public class UserCredentialsProvider implements IUserCredentialsProvider {

    private String credentials;

    public UserCredentialsProvider(String user, String password) {
        credentials = Credentials.basic(user, password);
    }

    @Override
    public String getUserCredentials() {
        return credentials;
    }
}
