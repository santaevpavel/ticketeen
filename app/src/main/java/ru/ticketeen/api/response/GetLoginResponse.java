package ru.ticketeen.api.response;


import com.google.gson.annotations.SerializedName;

public class GetLoginResponse {
    @SerializedName("email")
    public String email;

    @SerializedName("name")
    public String name;
}
