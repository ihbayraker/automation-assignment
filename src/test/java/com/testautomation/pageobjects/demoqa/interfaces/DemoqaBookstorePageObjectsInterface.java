package com.testautomation.pageobjects.demoqa.interfaces;

public interface DemoqaBookstorePageObjectsInterface extends PageObjectInterface {


    void login(String username, String Password);

    boolean checkLoginSuccess(String username) throws Exception;

    void ClickGoBackToBookstore();

    int countBooksByPublisher(String publisher);

    void clickBookByTitle(String title);

    boolean verifyBookIsbn(String isbn);

    boolean verifyBookTitle(String title);

    boolean verifyBookSubtitle(String subtitle);

    boolean verifyBookAuthor(String author);

    boolean verifyBookPublisher(String publisher);

    boolean verifyBookPages(String pages);

    boolean verifyBookDescription(String description);

    boolean verifyBookWebsite(String website);

    void logout();

}
