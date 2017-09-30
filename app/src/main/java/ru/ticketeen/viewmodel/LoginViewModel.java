package ru.ticketeen.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.ticketeen.App;
import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.preference.LoginPasswordPreference;
import ru.ticketeen.util.NetworkHelper;
import ru.ticketeen.util.RxUtil;

import static ru.ticketeen.preference.LoginPasswordPreference.EMPTY;

public class LoginViewModel extends ViewModel {

    public enum LoginStatus {
        UNKNOWN,
        IN_PROGRESS,
        SUCCESS,
        ERROR,
        NO_INTERNET,
    }

    @Inject
    LoginPasswordPreference loginPasswordPreference;

    @Inject
    ApiRequester apiRequester;

    private MutableLiveData<Boolean> progress;
    private MutableLiveData<LoginStatus> loginStatus;

    public LoginViewModel() {
        App.component().inject(this);

        progress = new MutableLiveData<>();
        loginStatus = new MutableLiveData<>();
        progress.setValue(false);
        loginStatus.setValue(LoginStatus.UNKNOWN);
    }

    public void login(String user, String password) {
        if (!new NetworkHelper().isInternetConnected(App.getContext())) {
            loginStatus.setValue(LoginStatus.NO_INTERNET);
            return;
        }
        progress.setValue(true);
        loginStatus.setValue(LoginStatus.IN_PROGRESS);
        loginPasswordPreference.setLogin(user);
        loginPasswordPreference.setPassword(password);
        RxUtil.makeRequest(() -> apiRequester.login(),
                throwable -> {
                    loginPasswordPreference.setLogin(EMPTY);
                    loginPasswordPreference.setPassword(EMPTY);
                    progress.setValue(false);
                    loginStatus.setValue(LoginStatus.ERROR);
                },
                getLoginResponse -> {
                    progress.setValue(false);
                    loginStatus.setValue(LoginStatus.SUCCESS);
                });
    }

    public MutableLiveData<Boolean> getProgress() {
        return progress;
    }

    public MutableLiveData<LoginStatus> getLoginStatus() {
        return loginStatus;
    }
}
