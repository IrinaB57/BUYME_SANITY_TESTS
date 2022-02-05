package org.buyme.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HowToSendSection {

    WebDriver driver;

    public HowToSendSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page objects
    @FindBy(xpath = "(//*[name()='path'][@class='circle'])[2]")
    private WebElement mailIcon;

    @FindBy(id = "email")
    private WebElement emailText;

    @FindBy(xpath = "(//*[name()='path'][@class='circle'])[1]")
    private WebElement smsIcon;

    @FindBy(id = "sms")
    private WebElement smsText;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@placeholder='מספר נייד']")
    private WebElement senderNameText;

    //    actions
    public HowToSendSection clickEmailIcon() {
        mailIcon.click();
        return this;
    }

    public HowToSendSection enterEmail(String emailId) {
        emailText.sendKeys(emailId);
        return this;
    }

    public HowToSendSection clickSmsIcon() {
        smsIcon.click();
        return this;
    }

    public HowToSendSection enterSms(String sms) {
        smsText.sendKeys(sms);
        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public HowToSendSection enterSenderMobileNumber(String name) {
        senderNameText.sendKeys(name);
        return this;
    }
}
