package com.testautomation.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Token extends ApiResponse {

    @SerializedName("token")
    private String token;
    @SerializedName("expires")
    private String expires;
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private String result;
}
