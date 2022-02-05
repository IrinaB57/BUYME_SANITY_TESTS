package org.buyme.pages;

import org.buyme.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SupplierPage {

    WebDriver driver;
    String URL = Configuration.getInstance().getProperty("application.url") + "supplier/";

    public SupplierPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page object
    @FindBy(xpath = "//input[@placeholder='הכנס סכום']")
    private WebElement enterAmountText;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement submitButton;

    //    actions
    public boolean isLoaded() {
        return driver.getCurrentUrl().startsWith(URL);
    }

    public void enterAmount(String amount) {
        enterAmountText.sendKeys(amount);
    }

    public void clickSubmit() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }
}
