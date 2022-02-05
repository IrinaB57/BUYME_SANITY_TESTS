package org.buyme.pages;

import org.buyme.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {

    WebDriver driver;
    String URL = Configuration.getInstance().getProperty("application.url") + "search";

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page objects
    @FindBy(xpath = "(//a[starts-with(@href,'https://buyme.co.il/supplier/')])[1]")
    private WebElement firstSearchResult;

    //    actions
    public void selectFirstSearchResult() {
        firstSearchResult.click();
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().startsWith(URL);
    }
}