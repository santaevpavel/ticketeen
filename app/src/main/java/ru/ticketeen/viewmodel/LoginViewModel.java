package ru.ticketeen.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.ticketeen.App;
import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.util.RxUtil;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> progress;
    private MutableLiveData<Boolean> loginStatus;

    @Inject
    ApiRequester apiRequester;

    public LoginViewModel() {
        App.component().inject(this);

        progress = new MutableLiveData<>();
        loginStatus = new MutableLiveData<>();
        progress.setValue(false);
        loginStatus.setValue(false);
    }

    public void login(String user, String password) {
        progress.setValue(true);
        RxUtil.makeRequest(() -> apiRequester.login(), throwable -> {
                },
                getLoginResponse -> loginStatus.setValue(true));
    }

    public void signUp(String phone, String email, String password) {

    }

    public MutableLiveData<Boolean> getProgress() {
        return progress;
    }

    public MutableLiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }
}
