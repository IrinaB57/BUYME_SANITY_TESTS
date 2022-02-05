package org.buyme.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewOfTheGiftSection {
    WebDriver driver;

    public ViewOfTheGiftSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page object
    @FindBy(css = ".recipient-sender.bottom-md")
    private WebElement giftInfoText;

    //      actions
    public boolean isTheReceiverNameDisplayed(String receiver) {
        return giftInfoText.getText().contains(receiver);
    }

    public boolean isTheSenderNameDisplayed(String firstName) {
        return giftInfoText.getText().contains(firstName);
    }
}
