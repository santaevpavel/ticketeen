package ru.ticketeen.preference;


public interface LoginPasswordPreference {

    boolean isLoggedIn();

    void setLogin(String login);

    void setPassword(String password);

    String getLogin();

    String getPassword();
}
