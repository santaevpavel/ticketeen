package ru.ticketeen.api.response;


import com.google.gson.annotations.SerializedName;

public class ExtractResponse {

    @SerializedName("url")
    public String url;

    public ExtractResponse(String url) {
        this.url = url;
    }
}
