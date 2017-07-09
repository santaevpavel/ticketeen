package ru.ticketeen.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.api.util.UserCredentialsProvider;

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
        return new ApiRequester("http://proverkacheka.nalog.ru:8888",
                new UserCredentialsProvider("+79139066994", "705697"));
    }
}
