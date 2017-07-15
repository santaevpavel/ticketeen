package ru.ticketeen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.api.response.GetLoginResponse;
import ru.ticketeen.api.response.GetTicketResponse;
import ru.ticketeen.api.util.UserCredentialsProvider;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FnsApiTest {

    private static final String FNS_API_URL = "http://proverkacheka.nalog.ru:8888";

    @Mock
    UserCredentialsProvider provider;

    @Before
    public void setUp() throws Exception {
        when(provider.getUserCredentials()).thenReturn("Basic Kzc5MTM5MDY2OTk0OjcwNTY5Nw==");
    }

    @Test
    public void extract() throws Exception {
        ApiRequester requester = new ApiRequester(FNS_API_URL, provider);
        GetTicketResponse tickets = requester.getTickets();
        assertNotNull("Url to download", tickets.url);
    }

    @Test
    public void login() throws Exception {
        ApiRequester requester = new ApiRequester(FNS_API_URL, provider);
        GetLoginResponse response = requester.login();
        assertNotNull("email", response.email);
        assertNotNull("name", response.name);
    }
}