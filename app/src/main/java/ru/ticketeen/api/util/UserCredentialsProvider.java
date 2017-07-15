package ru.ticketeen.api.util;


import okhttp3.Credentials;
import ru.ticketeen.preference.LoginPasswordPreference;

public class UserCredentialsProvider implements IUserCredentialsProvider {

    private LoginPasswordPreference preference;

    public UserCredentialsProvider(LoginPasswordPreference preference) {
        this.preference = preference;
    }

    @Override
    public String getUserCredentials() {
        return Credentials.basic(preference.getLogin(), preference.getPassword());
    }
}
