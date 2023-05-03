package com.testautomation.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

}
