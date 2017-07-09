package ru.ticketeen;

import org.junit.Test;

import ru.ticketeen.api.FnsApiRequester;
import ru.ticketeen.api.response.GetLoginResponse;
import ru.ticketeen.api.response.GetTicketResponse;
import ru.ticketeen.util.UserCredentialsProvider;

import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FnsApiTest {

    private static final String FNS_API_URL = "http://proverkacheka.nalog.ru:8888";
    private static final String LOGIN = "+79139066994";
    private static final String PASSWORD = "705697";

    @Test
    public void extract() throws Exception {
        FnsApiRequester requester = new FnsApiRequester(FNS_API_URL,
                new UserCredentialsProvider(LOGIN, PASSWORD));
        GetTicketResponse tickets = requester.getTickets();
        assertNotNull("Url to download", tickets.url);
    }

    @Test
    public void login() throws Exception {
        FnsApiRequester requester = new FnsApiRequester(FNS_API_URL,
                new UserCredentialsProvider(LOGIN, PASSWORD));
        GetLoginResponse response = requester.login();
        assertNotNull("email", response.email);
        assertNotNull("name", response.name);
    }
}