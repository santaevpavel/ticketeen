package ru.ticketeen.api;


import java.util.List;

import ru.ticketeen.api.response.ExtractResponse;
import ru.ticketeen.api.response.GetLoginResponse;
import ru.ticketeen.api.response.TicketsResponse;

interface ApiRequester {
    ExtractResponse getLinkToTickets();

    List<TicketsResponse> getTickets(String url);

    GetLoginResponse login();
}
