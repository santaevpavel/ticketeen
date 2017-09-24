package ru.ticketeen.preference;


import android.content.SharedPreferences;
import android.text.TextUtils;

public class LoginPasswordPreferenceImpl implements LoginPasswordPreference {

    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";

    private final SharedPreferences sharedPreferences;

    public LoginPasswordPreferenceImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public boolean isLoggedIn() {
        return !TextUtils.isEmpty(getLogin()) && !TextUtils.isEmpty(getPassword());
    }

    @Override
    public void setLogin(String login) {
        setString(LOGIN, login);
    }

    @Override
    public void setPassword(String password) {
        setString(PASSWORD, password);
    }

    @Override
    public String getLogin() {
        return getString(LOGIN);
    }

    @Override
    public String getPassword() {
        return getString(PASSWORD);
    }

    private void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    private String getString(String key) {
        return sharedPreferences.getString(key, "");
    }
}
