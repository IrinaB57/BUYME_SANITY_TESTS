package org.buyme.pages;

import org.buyme.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    WebDriver driver;
    String URL = Configuration.getInstance().getProperty("application.url");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page objects
    @FindBy(xpath = "//span[contains(text(),'כניסה / הרשמה')]")
    private WebElement loginOrRegisterButton;

    @FindBy(css = "span[class='text-link theme']")
    private WebElement toRegisterButton;

    @FindBy(id = "ember1696")
    private WebElement firstNameText;

    @FindBy(id = "ember1703")
    private WebElement emailIdText;

    @FindBy(id = "valPass")
    private WebElement passwordText;

    @FindBy(id = "ember1717")
    private WebElement reEnterPasswordText;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement registerButton;

    @FindBy(id = "chevron-down")
    private WebElement menuDropDown;

    @FindBy(xpath = "//a[@href='https://buyme.co.il/myAccount']")
    private WebElement myAccountDetailsButton;

    @FindBy(xpath = "//li[contains(text(),'כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה')]")
    private WebElement loginPasswordErrorMessage;

    //    actions
    public RegistrationPage navigateTo() {
        driver.get(URL);
        return this;
    }

    public RegistrationPage clickLoginOrRegistrationButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginOrRegisterButton));
        loginOrRegisterButton.click();
        return this;
    }

    public RegistrationPage clickToRegisterButton() {
        toRegisterButton.click();
        return this;
    }

    public RegistrationPage enterFirstName(String firstName) {
        firstNameText.sendKeys(firstName);
        return this;
    }

    public RegistrationPage enterEmailAddress(String emailId) {
        emailIdText.sendKeys(emailId);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        passwordText.sendKeys(password);
        return this;
    }

    public RegistrationPage enterPasswordAgain(String reEnterPassword) {
        reEnterPasswordText.sendKeys(reEnterPassword);
        return this;
    }

    public void clickRegisterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    public void navigateToMyAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(menuDropDown));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuDropDown).perform();
        myAccountDetailsButton.click();
    }

    public boolean isLoginPasswordErrorDisplayed() {
        return loginPasswordErrorMessage.isDisplayed();
    }
}
