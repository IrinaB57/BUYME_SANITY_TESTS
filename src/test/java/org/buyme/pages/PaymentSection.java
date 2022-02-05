package org.buyme.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentSection {

    WebDriver driver;

    public PaymentSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page object
    @FindBy(xpath = "//span[@class='giftcard-preview']")
    private WebElement viewPaymentIcon;

    // actions
    public void clickViewGiftIcon() {
        viewPaymentIcon.click();
    }
}
