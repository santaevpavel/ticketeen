package ru.ticketeen.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.ticketeen.api.response.ExtractResponse;
import ru.ticketeen.api.response.GetLoginResponse;
import ru.ticketeen.api.response.TicketsResponse;

interface FnsApi {

    String API_VERSION = "v1/";

    String FILE_TYPE_JSON = "json";

    @GET(API_VERSION + "extract")
    Call<ExtractResponse> getLinkToTickets(
            @Query("sendToEmail") int sendToEmail,
            @Query("fileType") String fileType);

    @GET(API_VERSION + "mobile/users/login")
    Call<GetLoginResponse> login();

    @GET("{url}")
    Call<List<TicketsResponse>> getTickets(@Path(value = "url", encoded = true) String url);
}
