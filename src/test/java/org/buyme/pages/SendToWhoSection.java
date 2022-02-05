package org.buyme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class SendToWhoSection {
    WebDriver driver;

    public SendToWhoSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page objects
    @FindBy(xpath = "//div[@gtm='למישהו אחר']")
    private WebElement toOthersButton;

    @FindBy(xpath = "//label[@id='friendName']//input")
    private WebElement receiverNameText;

    @FindBy(xpath = "//span[@title='לאיזה אירוע?']")
    private WebElement eventTypeDropDown;

    private WebElement selectEventTypeButton(String eventType) {
        return driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]", eventType)));
    }

    @FindBy(xpath = "//textarea[@placeholder='מזל טוב, תודה רבה או פשוט מלא אהבה? כאן כותבים מילים טובות ואיחולים שמחים']")
    private WebElement blessingText;

    @FindBy(xpath = "//input[@name='logo']")
    private WebElement uploadLogoButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

//    @FindBy(xpath = "//div[contains(text(),'למי לשלוח')]")
    @FindBy(xpath = "(//div[@class='number'])[1]//parent::div//parent::div//div")
    private WebElement sentToWhoText;

    //    actions
    public SendToWhoSection clickGiftToOthers() {
        toOthersButton.click();
        return this;
    }

    public SendToWhoSection enterReceiverName(String name) {
        receiverNameText.sendKeys(name);
        return this;
    }

    public SendToWhoSection selectEvent(String eventType) {
        eventTypeDropDown.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(selectEventTypeButton(eventType)));
        selectEventTypeButton(eventType).click();
        return this;
    }

    public SendToWhoSection enterBlessings(String text) {
        blessingText.sendKeys(text);
        return this;
    }

    public SendToWhoSection uploadLogo() {
        File resourcesDirectory = new File("src/test/resources");
        System.out.println(resourcesDirectory.getAbsolutePath() + "/smiley.png");
        uploadLogoButton.sendKeys(resourcesDirectory.getAbsolutePath() + "/smiley.png");
        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public String getColorOfSendToWhoText(){
        return sentToWhoText.getCssValue("color");
    }
}
