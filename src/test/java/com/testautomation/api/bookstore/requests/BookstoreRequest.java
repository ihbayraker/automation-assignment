package com.testautomation.api.bookstore.requests;

import com.google.gson.GsonBuilder;
import com.testautomation.api.bookstore.interfaces.BookstoreRequestInterface;
import com.testautomation.pojo.*;
import com.testautomation.stepdef.Hooks;
import com.testautomation.utils.Helper;
import com.testautomation.utils.PropertiesUtils;
import java.net.http.HttpResponse;
import java.util.List;

public class BookstoreRequest extends Request implements BookstoreRequestInterface {

    public BookstoreRequest() throws Exception {
        PropertiesUtils.setEnvironment("bookstore");
    }

    @Override
    public Account createAccount(String username, String password) throws Exception {
        Account account = new Account();
        account.setPostUserame(username);
        account.setPassword(password);

        String post = new GsonBuilder().create().toJson(account);
        HttpResponse<String> response = postRequest(PropertiesUtils.getEnvironmentProperty("bookstoreUserApi"),post,"false");

        Helper.scenarioWrite(Hooks.getScenario(),String.valueOf(response.statusCode()),"Status Code");
        Helper.scenarioWrite(Hooks.getScenario(),response.body(),"Response");

        account = new GsonBuilder().create().fromJson(response.body(), Account.class);
        account.setCode(response.statusCode());

        return account;
    }

    @Override
    public Token generateToken(String username, String password) throws Exception {
        Account account = new Account();
        account.setPostUserame(username);
        account.setPassword(password);

        String post = new GsonBuilder().create().toJson(account);
        HttpResponse<String> response = postRequest(PropertiesUtils.getEnvironmentProperty("bookstoreTokenApi"),post,"false");

        Helper.scenarioWrite(Hooks.getScenario(),String.valueOf(response.statusCode()),"Status Code");
        Helper.scenarioWrite(Hooks.getScenario(),response.body(),"Response");

        Token token = new GsonBuilder().create().fromJson(response.body(), Token.class);
        token.setCode(response.statusCode());

        return token;
    }

    @Override
    public Account getAccount(String userid, String auth) throws Exception {
        HttpResponse<String> response = getRequest(PropertiesUtils.getEnvironmentProperty("bookstoreUserApi")+"/"+userid,auth);

        Helper.scenarioWrite(Hooks.getScenario(),String.valueOf(response.statusCode()),"Status Code");
        Helper.scenarioWrite(Hooks.getScenario(),response.body(),"Response");

        Account account = new GsonBuilder().create().fromJson(response.body(), Account.class);
        account.setCode(response.statusCode());

        return account;
    }

    @Override
    public ApiResponse deleteAccount(String userid, String auth) throws Exception {
        HttpResponse<String> response = deleteRequest(PropertiesUtils.getEnvironmentProperty("bookstoreUserApi")+"/"+userid,auth);

        Helper.scenarioWrite(Hooks.getScenario(),String.valueOf(response.statusCode()),"Status Code");

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(response.statusCode());

        return apiResponse;
    }

    @Override
    public Books getBooks() throws Exception {
        HttpResponse<String> response = getRequest(PropertiesUtils.getEnvironmentProperty("bookstoreBooksApi"),"false");

        Helper.scenarioWrite(Hooks.getScenario(),String.valueOf(response.statusCode()),"Status Code");
        Helper.scenarioWrite(Hooks.getScenario(),response.body(),"Response");

        Books books = new GsonBuilder().create().fromJson(response.body(), Books.class);
        books.setCode(response.statusCode());
        return books;
    }

    @Override
    public Book getBook(String isbn) throws Exception {
        HttpResponse<String> response = getRequest(PropertiesUtils.getEnvironmentProperty("bookstoreBookApi")+"?ISBN="+isbn,"false");

        Helper.scenarioWrite(Hooks.getScenario(),String.valueOf(response.statusCode()),"Status Code");
        Helper.scenarioWrite(Hooks.getScenario(),response.body(),"Response");

        Book book = new GsonBuilder().create().fromJson(response.body(), Book.class);
        return book;
    }

    @Override
    public Account addBooks(String userid, List<Isbns> isbns, String auth) throws Exception {
        Account account = new Account();
        account.setPostUserId(userid);
        account.setIsbns(isbns);

        String post = new GsonBuilder().create().toJson(account);

        HttpResponse<String> response = postRequest(PropertiesUtils.getEnvironmentProperty("bookstoreBooksApi"),post,auth);

        Helper.scenarioWrite(Hooks.getScenario(),String.valueOf(response.statusCode()),"Status Code");
        Helper.scenarioWrite(Hooks.getScenario(),response.body(),"Response");

        account = new GsonBuilder().create().fromJson(response.body(), Account.class);
        account.setCode(response.statusCode());

        return account;
    }
}
