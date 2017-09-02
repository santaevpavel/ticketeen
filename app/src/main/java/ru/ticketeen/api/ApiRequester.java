package ru.ticketeen.api;


import java.util.List;

import ru.ticketeen.api.response.ExtractResponse;
import ru.ticketeen.api.response.GetLoginResponse;
import ru.ticketeen.api.response.TicketsResponse;
import ru.ticketeen.api.util.IUserCredentialsProvider;

import static ru.ticketeen.api.FnsApi.FILE_TYPE_JSON;

public class ApiRequester extends BaseApiRequester {

    public ApiRequester(String fnsApiBaseUrl, IUserCredentialsProvider userCredentialsProvider) {
        super(fnsApiBaseUrl, userCredentialsProvider);
    }

    public ExtractResponse getLinkToTickets() {
        return makeRequest(api.getLinkToTickets(0, FILE_TYPE_JSON));
    }

    public List<TicketsResponse> getTickets(String url) {
        return makeRequest(api.getTickets(url));
    }

    public GetLoginResponse login() {
        return makeRequest(api.login());
    }
}
