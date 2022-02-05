package org.buyme.testcases.extras;

import org.apache.commons.lang.RandomStringUtils;
import org.buyme.engine.BaseTest;
import org.buyme.pages.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static org.buyme.utils.extentreports.ExtentTestManager.startTest;

public class ExtraTest {

    //    variables for the testContexts and pages
    BaseTest baseTest;
    RegistrationPage registrationPage;
    MyAccountPage myAccountPage;
    SearchGiftSection searchGiftSection;
    SearchResultsPage searchResultsPage;
    SupplierPage supplierPage;
    PurchaseGiftPage purchaseGiftPage;
    SendToWhoSection sendToWhoSection;

    public ExtraTest() {
        baseTest = new BaseTest();
    }

    @BeforeClass
    public void setDriver(ITestContext context) {
        context.setAttribute("WebDriver", baseTest.getDriver());
    }

    @BeforeMethod
    public void setup() {
        registrationPage = new RegistrationPage(baseTest.getDriver());
        myAccountPage = new MyAccountPage(baseTest.getDriver());
        searchGiftSection = new SearchGiftSection(baseTest.getDriver());
        searchResultsPage = new SearchResultsPage(baseTest.getDriver());
        supplierPage = new SupplierPage(baseTest.getDriver());
        purchaseGiftPage = new PurchaseGiftPage(baseTest.getDriver());
        sendToWhoSection = new SendToWhoSection(baseTest.getDriver());
    }

    @Test
    public void loginErrorTest(Method method) {
        startTest(method.getName(), "Login error validation");

        registrationPage.navigateTo();
        registrationPage.clickLoginOrRegistrationButton();
        registrationPage.clickRegisterButton();
        Assert.assertTrue(registrationPage.isLoginPasswordErrorDisplayed());
    }

    @Test
    public void sendToHowColorTest(Method method) {
        startTest(method.getName(), "Send To How text color test");

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
        supplierPage.enterAmount("10");
        supplierPage.clickSubmit();

//        Step 4: Provide receiver and sender details and validate the same
        Assert.assertTrue(purchaseGiftPage.isLoaded());

//        Step 5: Validate the colour of "Send To Who" text
        Assert.assertEquals(sendToWhoSection.getColorOfSendToWhoText(), "rgba(255, 161, 38, 1)");
    }

    @AfterMethod
    public void tearDown() {
        baseTest.tearDown();
    }
}
