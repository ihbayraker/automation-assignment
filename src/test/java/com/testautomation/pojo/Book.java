package com.testautomation.pojo;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {

    @SerializedName("isbn")
    private String isbn;
    @SerializedName("title")
    private String title;
    @SerializedName("subTitle")
    private String subtitle;
    @SerializedName("author")
    private String author;
    @SerializedName("publish_date")
    private String publish_date;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("pages")
    private int pages;
    @SerializedName("description")
    private String description;
    @SerializedName("website")
    private String website;

}
