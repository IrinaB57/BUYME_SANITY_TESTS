package org.buyme.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchGiftSection {

    WebDriver driver;

    public SearchGiftSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    page objects
    @FindBy(xpath = "//span[@title='סכום']")
    private WebElement priceDropDownButton;

    @FindBy(xpath = "//span[@title='סכום']//parent::div//parent::div//li[@value='1']")
    private WebElement selectPriceButton;

    @FindBy(xpath = "//span[@title='אזור']")
    private WebElement areaDropDownButton;

    @FindBy(xpath = "//span[@title='אזור']//parent::div//parent::div//li[@value='13']")
    private WebElement selectAreaButton;

    @FindBy(xpath = "//span[@title='קטגוריה']")
    private WebElement categoryDropDownButton;

    @FindBy(xpath = "//span[@title='קטגוריה']//parent::div//parent::div//li[@value='8']")
    private WebElement selectCategoryButton;

    @FindBy(xpath = "(//a[starts-with(@href,'https://buyme.co.il/search')])[1]")
    private WebElement findButton;

    //    actions
    public SearchGiftSection selectPrice() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(priceDropDownButton));
        priceDropDownButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(selectPriceButton));
        selectPriceButton.click();
        return this;
    }

    public SearchGiftSection selectArea() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(areaDropDownButton));
        areaDropDownButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(selectAreaButton));
        selectAreaButton.click();
        return this;
    }

    public SearchGiftSection selectCategory() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(categoryDropDownButton));
        categoryDropDownButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(selectCategoryButton));
        selectCategoryButton.click();
        return this;
    }

    public void clickFindButton() {
        findButton.click();
    }
}
