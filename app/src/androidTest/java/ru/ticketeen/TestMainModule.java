package ru.ticketeen;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.api.response.ExtractResponse;
import ru.ticketeen.api.response.GetLoginResponse;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.preference.LoginPasswordPreference;
import ru.ticketeen.preference.LoginPasswordPreferenceImpl;

@Module
public class TestMainModule {
    Application app;

    public TestMainModule(Application app) {
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
        return new ApiRequester() {

            @Override
            public ExtractResponse getLinkToTickets() {
                final ExtractResponse extractResponse = new ExtractResponse();
                extractResponse.url = "mockUrl";
                return extractResponse;
            }

            @Override
            public List<TicketsResponse> getTickets(String url) {
                final ArrayList<TicketsResponse> ticketsResponses = new ArrayList<>();
                final TicketsResponse ticketsResponse = new TicketsResponse();
                ticketsResponse.document = new TicketsResponse.Document();
                ticketsResponse.document.receipt = new TicketsResponse.Receipt();
                ticketsResponse.document.receipt.dateTime = "mockDateTime";
                ticketsResponses.add(ticketsResponse);
                return ticketsResponses;
            }

            @Override
            public GetLoginResponse login() {
                return null;
            }
        };

        /*new ApiRequesterImpl("http://proverkacheka.nalog.ru:8888",
                new UserCredentialsProvider(new LoginPasswordPreference() {
                    @Override
                    public String getLogin() {
                        return "+79139066994";
                    }

                    @Override
                    public void setLogin(String login) {
                    }

                    @Override
                    public String getPassword() {
                        return "705697";
                    }

                    @Override
                    public void setPassword(String password) {
                    }
                }));*/
    }
}
