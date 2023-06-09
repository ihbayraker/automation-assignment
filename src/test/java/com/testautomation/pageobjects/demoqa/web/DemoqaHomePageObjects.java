package com.testautomation.pageobjects.demoqa.web;

import com.testautomation.pageobjects.demoqa.interfaces.DemoqaHomePageObjectsInterface;
import com.testautomation.utils.Helper;
import com.testautomation.utils.PropertiesUtils;

public class DemoqaHomePageObjects extends PageObject implements DemoqaHomePageObjectsInterface {

    public DemoqaHomePageObjects() throws Exception {
        super();
        PropertiesUtils.setEnvironment("demoqa");
    }

    @Override
    public void navigateToHomepage() {
        browser.navigate().to(PropertiesUtils.getEnvironmentProperty("homepage"));
        closeGoogleAd();
    }

    @Override
    public void closeGoogleAd() {
        try{
            Helper.clickByXpath(PropertiesUtils.getEnvironmentProperty("googleAdXpath"),browser);
        }catch (Exception ignored){}
    }

    @Override
    public void navigateToBookStoreApplication() {
        navigateToHomepage();
        Helper.clickByXpath(PropertiesUtils.getEnvironmentProperty("homepageCardXpath")+"[6]",browser);
    }

    @Override
    public void selectItem(int i) {
        Helper.clickByXpath(PropertiesUtils.getEnvironmentProperty("itemSelectionXpath")+"["+i+"]",browser);
    }
}
