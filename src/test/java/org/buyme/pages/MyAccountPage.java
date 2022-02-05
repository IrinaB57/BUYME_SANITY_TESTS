package org.buyme.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page objects
    @FindBy(xpath = "//span[contains(text(),'שם פרטי')]//parent::div//parent::label//input")
    private WebElement firstNameText;

    @FindBy(xpath = "//span[contains(text(),'אימייל (שם משתמש)')]//parent::div//parent::label//input")
    private WebElement emailIdText;

    //    actions
    public String readFirstName() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].value", firstNameText);
    }

    public String readEmailId() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].value", emailIdText);
    }
}
