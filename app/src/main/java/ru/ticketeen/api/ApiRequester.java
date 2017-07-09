package ru.ticketeen.api;


import ru.ticketeen.api.response.GetLoginResponse;
import ru.ticketeen.api.response.GetTicketResponse;
import ru.ticketeen.api.util.IUserCredentialsProvider;

import static ru.ticketeen.api.FnsApi.FILE_TYPE_JSON;

public class ApiRequester extends BaseApiRequester {

    public ApiRequester(String fnsApiBaseUrl, IUserCredentialsProvider userCredentialsProvider) {
        super(fnsApiBaseUrl, userCredentialsProvider);
    }

    public GetTicketResponse getTickets() {
        return makeRequest(api.getTickets(0, FILE_TYPE_JSON));
    }

    public GetLoginResponse login() {
        return makeRequest(api.login());
    }
}
