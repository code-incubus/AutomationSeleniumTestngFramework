package tests.login;

import data.CommonTestStrings;
import data.TestGroups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.BaseTest;
import utils.DateTimeUtils;
import utils.PropertiesUtils;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN})
public class UnsuccessfulLoginWrongUsername extends BaseTest {

    private String sTestName = this.getClass().getName();
    private WebDriver driver;

    private String sUsername;
    private String sPassword;

    @BeforeMethod
    public void setupTest() {
        logger.info("[SETUP TEST] " + sTestName);
        driver = setUpDriver();
        sUsername = PropertiesUtils.getAdminUsername();
        sPassword = PropertiesUtils.getAdminPassword() + "!";
    }

    @Test
    public void testUnsuccessfulLoginWrongUsername() {
        String sExpectedLoginErrorMessage = CommonTestStrings.getLoginErrorMessage();
        logger.info("[START TEST] " + sTestName);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPage();
        loginPage.typeUsername(sUsername);
        loginPage.typePassword(sPassword);
        //loginPage.clickOnLoginButtonNoProgress(); //TODO This line here can cause exceptions StaleElement... because DOM structure at this point will be 'refreshed'.
        loginPage = loginPage.clickOnLoginButtonNoProgress();
        DateTimeUtils.wait(Time.TIME_SHORT); //TODO This line should be removed and replaced with appropriate method for waitForElementToBeDisplayed
        String sErrorMessage = loginPage.getErrorLoginMessage();
        Assert.assertEquals(sErrorMessage, sExpectedLoginErrorMessage, "Wrong Login Error Message.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        logger.info("[END TEST] " + sTestName);
        tearDown(driver, testResult);
    }
}
