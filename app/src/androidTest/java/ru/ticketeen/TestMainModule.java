package ru.ticketeen;

import android.app.Application;
import android.content.SharedPreferences;

import org.mockito.Mockito;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.api.response.ExtractResponse;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.preference.LoginPasswordPreference;
import ru.ticketeen.preference.LoginPasswordPreferenceImpl;

import static org.mockito.Mockito.when;

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
    ApiRequester provideApiRequester() {
        final ApiRequester mockApiRequester = Mockito.mock(ApiRequester.class);
        when(mockApiRequester.getLinkToTickets())
                .thenReturn(new ExtractResponse("mockUrl"));

        final ArrayList<TicketsResponse> ticketsResponses = new ArrayList<>();
        final TicketsResponse.Receipt receipt = new TicketsResponse.Receipt();
        receipt.dateTime = "mockDateTime";
        ticketsResponses.add(new TicketsResponse(new TicketsResponse.Document(receipt)));

        when(mockApiRequester.getTickets(Mockito.anyString()))
                .thenReturn(ticketsResponses);

        return mockApiRequester;
    }
}
