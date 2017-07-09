package ru.ticketeen.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> progress;
    private MutableLiveData<Boolean> loginStatus;

    public LoginViewModel() {
        progress = new MutableLiveData<>();
        progress.setValue(false);
    }

    public void login(String user, String password) {
        progress.setValue(true);
        new Handler().postDelayed(() -> progress.setValue(false), 4000);
    }

    public void signUp(String phone, String email, String password) {

    }

    public MutableLiveData<Boolean> getProgress() {
        return progress;
    }
}
