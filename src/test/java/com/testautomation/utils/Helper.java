package com.testautomation.utils;

import io.cucumber.java.Scenario;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;

public class Helper {

    final static int timeout = 10;
    final static int polling = 1;

    private static FluentWait<WebDriver> initWait(WebDriver driver){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(timeout));
        wait.pollingEvery(Duration.ofSeconds(polling));
        wait.ignoring(NoSuchElementException.class);
        return wait;
    }


    private static WebElement findElementByXpath(String xpath, WebDriver driver){
        return driver.findElement(By.xpath(xpath));
    }

    private static void scrollElement(String xpath, WebDriver driver) {
        WebElement webElement = findElementByXpath(xpath,driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", webElement);
    }

    private static void clearText(String xpath, WebDriver driver) {
        WebElement webElement = findElementByXpath(xpath,driver);
        webElement.clear();
    }

    public static void WaitForElementPresentByXpath(String xpath, WebDriver driver){
        FluentWait<WebDriver> wait = initWait(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void clickByXpath(String xpath, WebDriver driver){
        scrollElement(xpath, driver);
        findElementByXpath(xpath, driver).click();
    }

    public static void sendKeysByXpath(String xpath, WebDriver driver, String keys, boolean clear){
        scrollElement(xpath, driver);

        if(clear){
            clearText(xpath,driver);
        }

        findElementByXpath(xpath, driver).sendKeys(keys);
    }

    public static int getElementCount(String xpath, WebDriver driver){
        return driver.findElements(By.xpath(xpath)).size();

    }

    public static String getTextByXpath(String xpath, WebDriver driver){
        return findElementByXpath(xpath, driver).getText();
    }

    public static void scenarioWrite(Scenario scenario, String text, String name) {
        scenario.attach(text,"text/plain",name);
    }

    public static String generateAlphanumericString(int i){
        return RandomStringUtils.randomAlphanumeric(i-2)+RandomStringUtils.randomNumeric(1)+"*";
    }
}
