package com.testautomation.api.bookstore.interfaces;

import com.github.mizosoft.methanol.MultipartBodyPublisher;

import java.net.http.HttpResponse;

public interface RequestInterface {

    HttpResponse<String> getRequest(String url, String auth) throws Exception;

    HttpResponse<String> deleteRequest(String url, String auth) throws Exception;

    HttpResponse<String> postRequest(String url, String post, String auth) throws Exception;
}
