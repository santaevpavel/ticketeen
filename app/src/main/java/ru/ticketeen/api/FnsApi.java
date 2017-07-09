package ru.ticketeen.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.ticketeen.api.response.GetTicketResponse;

interface FnsApi {

    String API_VERSION = "v1/";

    String FILE_TYPE_JSON = "json";

    @GET(API_VERSION + "extract")
    Call<GetTicketResponse> getTickets(
            @Query("sendToEmail") int sendToEmail,
            @Query("fileType") String fileType);

    @GET(API_VERSION + "mobile/users/login")
    Call<GetTicketResponse> login();

//    http://proverkacheka.nalog.ru:8888/v1/mobile/users/login

}
