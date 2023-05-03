package com.testautomation.stepdef;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.testautomation.api.bookstore.requests.BookstoreRequest;
import com.testautomation.pageobjects.demoqa.web.DemoqaBookstorePageObjects;
import com.testautomation.pageobjects.demoqa.web.DemoqaHomePageObjects;
import com.testautomation.pojo.*;
import com.testautomation.utils.Helper;
import com.testautomation.utils.WebDriverUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class BookstoreStepDef {
    private final BookstoreRequest bookstoreRequest;
    private final DemoqaHomePageObjects demoqaHomePageObjects;
    private final DemoqaBookstorePageObjects demoqaBookstorePageObjects;

    private Account account = new Account();
    private Token token = new Token();
    private List<String> isbn = new ArrayList<>();
    private String pass;

    public BookstoreStepDef() throws Exception {
        bookstoreRequest = new BookstoreRequest();
        demoqaHomePageObjects = new DemoqaHomePageObjects();
        demoqaBookstorePageObjects = new DemoqaBookstorePageObjects();
    }

    @Given("I create a new bookstore account")
    public void iCreateANewBookstoreAccount() throws Exception {
        String username = Helper.generateAlphanumericString(8);
        String password = Helper.generateAlphanumericString(12);
        account = bookstoreRequest.createAccount(username,password);
        pass = password;

        Assert.assertEquals("User creation was a failure", 201, account.getCode());
    }

    @And("I generate a Token for the created account")
    public void iGenerateATokenForTheNewlyCreatedAccount() throws Exception {
        token = bookstoreRequest.generateToken(account.getResponseUsername(),pass);
        Assert.assertEquals("Failed to generate a token", 200, token.getCode());
    }

    @And("I filter available books with {string}")
    public void iFilterAvailableBooksWith(String filter) throws Exception {
        Books response = bookstoreRequest.getBooks();
        Assert.assertEquals("Could not get the book list", 200, response.getCode());

        List<Book> books = response.getBooks();

        for(int i = 0; i<books.size(); i++){
            Book book = books.get(i);
            if(book.getPublisher().equals(filter)){
                isbn.add(book.getIsbn());
            }
        }
        Helper.scenarioWrite(Hooks.getScenario(),isbn.toString(),"Filtered Books");
    }

    @And("I add filtered books to the created account")
    public void iAddFilteredBooksToTheCreatedAccount() throws Exception {
        List<Isbns> isbns = new ArrayList<>();

        for(int i = 0; i<isbn.size(); i++){
            Isbns tempIsbns = new Isbns();
            tempIsbns.setIsbn(isbn.get(i));
            isbns.add(tempIsbns);
        }

        Account books = bookstoreRequest.addBooks(account.getResponseUserId(),isbns,token.getToken());
        Assert.assertEquals("Couldn't add books to the account", 201, books.getCode());
    }

    @Then("I verify that books are added to the account")
    public void iVerifyThatBooksAreAddedToTheAccount() throws Exception {
        account = bookstoreRequest.getAccount(account.getResponseUserId(),token.getToken());

        for(int i = 0; i<isbn.size(); i++){
            Assert.assertTrue("Could not find the book with isbn: "+isbn.get(i),account.getBooks().toString().contains(isbn.get(i)));
        }
    }

    @And("I delete the account I created")
    public void iDeleteTheAccountICreated() throws Exception {
        ApiResponse response = bookstoreRequest.deleteAccount(account.getResponseUserId(),token.getToken());
        Assert.assertEquals("Failed to delete the user", 200, response.getCode());
    }

    @And("I navigate to Bookstore page")
    public void iNavigateToBookstorePage() {
        demoqaHomePageObjects.navigateToBookStoreApplication();
    }

    @And("I click Login")
    public void iClickLogin() {
        demoqaHomePageObjects.selectItem(1);
    }

    @And("I enter the created username, password and press login")
    public void iEnterTheCreatedUsernamePasswordAndPressLogin() {
        demoqaBookstorePageObjects.login(account.getResponseUsername(),pass);
    }

    @Then("I validate that i Successfully logged in")
    public void iValidateThatISuccessfullyLoggedIn() throws Exception {
        Assert.assertTrue("Login was a failure", demoqaBookstorePageObjects.checkLoginSuccess(account.getResponseUsername()));
        WebDriverUtils.takeScreenshot(Hooks.getScenario(),"Screenshot");

    }

    @Then("I validate the books from {string} added to the account")
    public void iValidateTheBooksFromAddedToTheAccount(String filter) {
        Assert.assertEquals("Not all books that were added was visible on the UI",isbn.size(),demoqaBookstorePageObjects.countBooksByPublisher(filter));
    }

    @Then("I validate the details of the books in my collection")
    public void iValidateTheDetailsOfTheBooksInMyCollection() throws Exception {
        for(int i = 0; i<isbn.size(); i++){
            Book book = bookstoreRequest.getBook(isbn.get(i));
            demoqaBookstorePageObjects.clickBookByTitle(book.getTitle());

            Assert.assertTrue("Isbn of the book was wrong",demoqaBookstorePageObjects.verifyBookIsbn(book.getIsbn()));
            Assert.assertTrue("Title of the book was wrong",demoqaBookstorePageObjects.verifyBookTitle(book.getTitle()));
            Assert.assertTrue("Subtitle of the book was wrong",demoqaBookstorePageObjects.verifyBookSubtitle(book.getSubtitle()));
            Assert.assertTrue("Author of the book was wrong",demoqaBookstorePageObjects.verifyBookAuthor(book.getAuthor()));
            Assert.assertTrue("Publisher of the book was wrong",demoqaBookstorePageObjects.verifyBookPublisher(book.getPublisher()));
            Assert.assertTrue("Pages of the book was wrong",demoqaBookstorePageObjects.verifyBookPages(String.valueOf(book.getPages())));
            Assert.assertTrue("Description of the book was wrong",demoqaBookstorePageObjects.verifyBookDescription(book.getDescription()));
            Assert.assertTrue("Website of the book was wrong",demoqaBookstorePageObjects.verifyBookWebsite(book.getWebsite()));

            WebDriverUtils.takeScreenshot(Hooks.getScenario(),"Screenshot");
            demoqaBookstorePageObjects.ClickGoBackToBookstore();
        }
    }

    @And("I log out of the account")
    public void iLogOutOfTheAccount() {
        demoqaBookstorePageObjects.logout();
    }

}
