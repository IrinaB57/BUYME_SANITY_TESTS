package org.buyme.pages;

import org.buyme.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PurchaseGiftPage {
    WebDriver driver;
    String URL = Configuration.getInstance().getProperty("application.url") + "money/";

    public PurchaseGiftPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    actions
    public boolean isLoaded() {
        return driver.getCurrentUrl().startsWith(URL);
    }
}
