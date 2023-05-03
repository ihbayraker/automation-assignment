package com.testautomation.pojo;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Books extends ApiResponse {

    @SerializedName("books")
    private List<Book> books = null;
}
