package ru.ticketeen.util;


import okhttp3.Credentials;
import ru.ticketeen.api.util.IUserCredentialsProvider;

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
