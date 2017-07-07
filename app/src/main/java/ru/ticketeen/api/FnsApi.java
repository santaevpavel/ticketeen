package ru.ticketeen.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.ticketeen.api.response.GetReceiptsResponse;

interface FnsApi {

    String API_VERSION = "v1/";

    @GET(API_VERSION + "extract")
    Call<GetReceiptsResponse> getReceipts(
            @Query("sendToEmail") int sendToEmail,
            @Query("fileType") String json);

}
