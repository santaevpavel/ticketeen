package ru.ticketeen.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.api.util.UserCredentialsProvider;
import ru.ticketeen.preference.LoginPasswordPreference;

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
    ApiRequester provideApiRequester() {
        final LoginPasswordPreference preference = new LoginPasswordPreference() {

            @Override
            public void setLogin() {

            }

            @Override
            public void setPassword() {

            }

            @Override
            public String getLogin() {
                return "+79139066994";
            }

            @Override
            public String getPassword() {
                return "705697";
            }
        };
        return new ApiRequester("http://proverkacheka.nalog.ru:8888",
                new UserCredentialsProvider(preference));
    }
}
