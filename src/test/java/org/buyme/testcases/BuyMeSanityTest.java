package org.buyme.testcases;

import org.apache.commons.lang.RandomStringUtils;
import org.buyme.engine.BaseTest;
import org.buyme.pages.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static org.buyme.utils.extentreports.ExtentTestManager.startTest;

public class BuyMeSanityTest {

    //    variables for the testContexts and pages
    BaseTest baseTest;
    RegistrationPage registrationPage;
    MyAccountPage myAccountPage;
    SearchGiftSection searchGiftSection;
    SearchResultsPage searchResultsPage;
    SupplierPage supplierPage;
    PurchaseGiftPage purchaseGiftPage;
    SendToWhoSection sendToWhoSection;
    HowToSendSection howToSendSection;
    PaymentSection paymentSection;
    ViewOfTheGiftSection viewOfTheGiftSection;

    @BeforeClass
    public void setDriver(ITestContext context){
        context.setAttribute("WebDriver", baseTest.getDriver());
    }

    /**
     * Instantiate TestContext class objects
     */
    public BuyMeSanityTest() {
        baseTest = new BaseTest();
    }

    /**
     * Instantiate page class objects
     */
    @BeforeMethod
    public void setup() {
        registrationPage = new RegistrationPage(baseTest.getDriver());
        myAccountPage = new MyAccountPage(baseTest.getDriver());
        searchGiftSection = new SearchGiftSection(baseTest.getDriver());
        searchResultsPage = new SearchResultsPage(baseTest.getDriver());
        supplierPage = new SupplierPage(baseTest.getDriver());
        purchaseGiftPage = new PurchaseGiftPage(baseTest.getDriver());
        sendToWhoSection = new SendToWhoSection(baseTest.getDriver());
        howToSendSection = new HowToSendSection(baseTest.getDriver());
        paymentSection = new PaymentSection(baseTest.getDriver());
        viewOfTheGiftSection = new ViewOfTheGiftSection(baseTest.getDriver());
    }

    /**
     * This test is a sanity check of BuyMe application.
     * Step 1: Register the user and validate the user details in the "My accounts" page
     * Step 2: Search for a gift from the home page
     * Step 3: Pick a gift
     * Step 4: Provide receiver and sender details and validate the same
     */
    @Test
    public void buyMeSanityTest(Method method) {

        startTest(method.getName(), "Buy Me sanity checks");

//        Step 1: Register the user and validate the user details in the "My accounts" page
        String firstName = "John";
        String password = "Abcdefg1";
        String emailId = RandomStringUtils.randomAlphabetic(10) + "@email.com";
        registrationPage.navigateTo()
                .clickLoginOrRegistrationButton()
                .clickToRegisterButton()
                .enterFirstName(firstName)
                .enterEmailAddress(emailId)
                .enterPassword(password)
                .enterPasswordAgain(password)
                .clickRegisterButton();

        registrationPage.navigateToMyAccount();

        Assert.assertEquals(myAccountPage.readFirstName(), firstName);
        Assert.assertEquals(myAccountPage.readEmailId(), emailId);

//        Step 2: Search for a gift from the home page
        registrationPage.navigateTo();
        searchGiftSection.selectPrice()
                .selectArea()
                .selectCategory()
                .clickFindButton();

//        Step 3: Pick a gift
        Assert.assertTrue(searchResultsPage.isLoaded());
        searchResultsPage.selectFirstSearchResult();

        Assert.assertTrue(supplierPage.isLoaded());
        supplierPage.enterAmount("100");
        supplierPage.clickSubmit();

//        Step 4: Provide receiver and sender details and validate the same
        Assert.assertTrue(purchaseGiftPage.isLoaded());

        String receiver = "Karin";
        sendToWhoSection.clickGiftToOthers()
                .enterReceiverName(receiver)
                .selectEvent("יום הולדת")
                .enterBlessings("Long Live")
                .uploadLogo()
                .clickSubmit();

        String mobileNumber = "0535501165";
        howToSendSection.clickEmailIcon()
                .enterEmail(emailId)
                .clickSmsIcon()
                .enterSms(mobileNumber)
                .enterSenderMobileNumber(mobileNumber)
                .clickSubmit();

        paymentSection.clickViewGiftIcon();

        Assert.assertTrue(viewOfTheGiftSection.isTheReceiverNameDisplayed(receiver));
        Assert.assertTrue(viewOfTheGiftSection.isTheSenderNameDisplayed(firstName));
    }

    /**
     * Tear down the browser after each method
     */
    @AfterMethod
    public void tearDown() {
        baseTest.tearDown();
    }
}
