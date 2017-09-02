package ru.ticketeen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import ru.ticketeen.api.ApiRequester;
import ru.ticketeen.api.response.ExtractResponse;
import ru.ticketeen.api.response.GetLoginResponse;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.api.util.UserCredentialsProvider;

import static org.junit.Assert.assertFalse;
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
    public void extractLinkToTickets() throws Exception {
        ApiRequester requester = new ApiRequester(FNS_API_URL, provider);
        ExtractResponse linkToTickets = requester.getLinkToTickets();
        assertNotNull("Url to download", linkToTickets.url);
    }

    @Test
    public void extractTickets() throws Exception {
        ApiRequester requester = new ApiRequester(FNS_API_URL, provider);
        ExtractResponse linkToTickets = requester.getLinkToTickets();
        assertNotNull("Url to download", linkToTickets.url);

        final List<TicketsResponse> tickets = requester.getTickets(linkToTickets.url);
        assertFalse("Document is empty", tickets.isEmpty());
    }

    @Test
    public void login() throws Exception {
        ApiRequester requester = new ApiRequester(FNS_API_URL, provider);
        GetLoginResponse response = requester.login();
        assertNotNull("email", response.email);
        assertNotNull("name", response.name);
    }
}