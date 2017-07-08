package ru.ticketeen;

import org.junit.Test;

import ru.ticketeen.api.ApiHelper;
import ru.ticketeen.api.response.GetReceiptsResponse;

import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FnsApiTest {

    private static final String FNS_API_URL = "http://proverkacheka.nalog.ru:8888";
    private static final String LOGIN = "+xxx";
    private static final String PASSWORD = "xxx";

    @Test
    public void test() throws Exception {
        ApiHelper.init(FNS_API_URL, LOGIN, PASSWORD);
        GetReceiptsResponse receipts = ApiHelper.getInstance().getReceipts();
        assertNotNull("Url to download", receipts.url);
    }
}