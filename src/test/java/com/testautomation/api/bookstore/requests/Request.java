package com.testautomation.api.bookstore.requests;


import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.testautomation.api.bookstore.interfaces.RequestInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Request implements RequestInterface {

    @Override
    public HttpResponse<String> getRequest(String url, String auth) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .headers("Content-Type","application/json","Authorization","Bearer "+auth)
                .timeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public HttpResponse<String> deleteRequest(String url, String auth) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(url))
                .headers("Content-Type","application/json","Authorization","Bearer "+auth)
                .timeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public HttpResponse<String> postRequest(String url, String post, String auth) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(post))
                .uri(URI.create(url))
                .headers("Content-Type","application/json","Authorization","Bearer "+auth)
                .timeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
