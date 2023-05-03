package com.testautomation.pageobjects.demoqa.web;

import com.testautomation.pageobjects.demoqa.interfaces.DemoqaBookstorePageObjectsInterface;
import com.testautomation.utils.Helper;
import com.testautomation.utils.PropertiesUtils;
import org.openqa.selenium.NoSuchElementException;

public class DemoqaBookstorePageObjects extends PageObject implements DemoqaBookstorePageObjectsInterface {

    public DemoqaBookstorePageObjects() throws Exception {
        super();
        PropertiesUtils.setEnvironment("demoqa");
    }

    @Override
    public void login(String username, String Password){
        Helper.sendKeysByXpath(PropertiesUtils.getEnvironmentProperty("bookstoreUsernameXpath"),browser,username,true);
        Helper.sendKeysByXpath(PropertiesUtils.getEnvironmentProperty("bookstorePasswordXpath"),browser,Password,true);
        Helper.clickByXpath(PropertiesUtils.getEnvironmentProperty("bookstoreLoginButtonXpath"),browser);
    }


    @Override
    public boolean checkLoginSuccess(String username) throws Exception {
        try{
            Helper.WaitForElementPresentByXpath(PropertiesUtils.getEnvironmentProperty("bookstoreLoginSuccessXpath"),browser);
        }catch (NoSuchElementException ignored){
            return false;
        }
        String output = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("bookstoreLoginSuccessXpath"),browser);
        return output.contains(username);
    }

    @Override
    public void ClickGoBackToBookstore(){
        Helper.clickByXpath(PropertiesUtils.getEnvironmentProperty("bookstoreGoBackXpath"),browser);
    }

    @Override
    public int countBooksByPublisher(String publisher){
        return Helper.getElementCount("(//*[text()[contains(.,'"+publisher+"')]])",browser);
    }

    @Override
    public void clickBookByTitle(String title){
        Helper.clickByXpath("(//*[text()[contains(.,'"+title+"')]])",browser);
    }

    @Override
    public boolean verifyBookIsbn(String isbn){
        String value = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("BookstoreIsbnXpath"), browser);
        return value.equals(isbn);
    }

    @Override
    public boolean verifyBookTitle(String title){
        String value = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("BookstoreTitleXpath"), browser);
        return value.equals(title);
    }

    @Override
    public boolean verifyBookSubtitle(String subtitle){
        String value = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("BookstoreSubtitleXpath"), browser);
        return value.equals(subtitle);
    }

    @Override
    public boolean verifyBookAuthor(String author){
        String value = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("BookstoreAuthorXpath"), browser);
        return value.equals(author);
    }

    @Override
    public boolean verifyBookPublisher(String publisher){
        String value = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("BookstorePublisherXpath"), browser);
        return value.equals(publisher);
    }

    @Override
    public boolean verifyBookPages(String pages){
        String value = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("BookstorePagesXpath"), browser);
        return value.equals(pages);
    }

    @Override
    public boolean verifyBookDescription(String description){
        String value = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("BookstoreDescriptionXpath"), browser);
        String value2 = value+" ";

        if(value.equals(description)){
            return true;
        }else return value2.equals(description);
    }

    @Override
    public boolean verifyBookWebsite(String website){
        String value = Helper.getTextByXpath(PropertiesUtils.getEnvironmentProperty("BookstoreWebsiteXpath"), browser);
        return value.equals(website);
    }


    @Override
    public void logout(){
        Helper.clickByXpath(PropertiesUtils.getEnvironmentProperty("bookstoreLogoutButtonXpath"), browser);
    }

}
