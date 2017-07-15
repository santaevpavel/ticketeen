package ru.ticketeen.di;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.api.util.UserCredentialsProvider;
import ru.ticketeen.preference.LoginPasswordPreference;
import ru.ticketeen.preference.LoginPasswordPreferenceImpl;

@Module
public class MainModule {
    Application app;

    public MainModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        final String APP_PREFS = "APP_PREFS";
        return app.getApplicationContext().getSharedPreferences(APP_PREFS, 0);
    }

    @Provides
    @Singleton
    LoginPasswordPreference provideLoginPasswordPreference(SharedPreferences sharedPreferences) {
        return new LoginPasswordPreferenceImpl(sharedPreferences);
    }

    @Provides
    @Singleton
    ApiRequester provideApiRequester(LoginPasswordPreference loginPasswordPreference) {
        return new ApiRequester("http://proverkacheka.nalog.ru:8888",
                new UserCredentialsProvider(loginPasswordPreference));
    }
}
