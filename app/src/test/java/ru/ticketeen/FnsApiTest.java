package ru.ticketeen;

import org.junit.Test;

import ru.ticketeen.api.ApiHelper;
import ru.ticketeen.api.response.GetReceiptsResponse;
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
    public void test() throws Exception {
        ApiHelper apiHelper = new ApiHelper(FNS_API_URL, new UserCredentialsProvider(LOGIN, PASSWORD));
        GetReceiptsResponse receipts = apiHelper.getReceipts();
        assertNotNull("Url to download", receipts.url);
    }
}