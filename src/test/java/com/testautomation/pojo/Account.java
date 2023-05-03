package com.testautomation.pojo;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Account extends ApiResponse {

    @SerializedName("userName")
    private String postUserame;
    @SerializedName("username")
    private String responseUsername;
    @SerializedName("password")
    private String password;
    @SerializedName("userId")
    private String postUserId;
    @SerializedName("userID")
    private String responseUserId;
    @SerializedName("collectionOfIsbns")
    private List<Isbns> isbns = null;
    @SerializedName("books")
    private List<Book> books = null;
}
