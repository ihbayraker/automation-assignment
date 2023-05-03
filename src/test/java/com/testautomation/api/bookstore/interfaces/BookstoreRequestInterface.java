package com.testautomation.api.bookstore.interfaces;

import com.testautomation.pojo.*;

import java.util.List;

public interface BookstoreRequestInterface {
    Account createAccount(String username, String password) throws Exception;

    Token generateToken(String username, String password) throws Exception;

    Account getAccount(String userid, String auth) throws Exception;

    ApiResponse deleteAccount(String userid, String auth) throws Exception;

    Books getBooks() throws Exception;

    Book getBook(String isbn) throws Exception;

    Account addBooks(String userid, List<Isbns> isbns, String auth) throws Exception;
}
