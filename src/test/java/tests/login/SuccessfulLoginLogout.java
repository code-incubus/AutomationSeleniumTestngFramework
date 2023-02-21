package tests.login;

import data.TestGroups;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DevicesPage;
import pages.LoginPage;
import tests.BaseTest;
import utils.PropertiesUtils;

@Test(groups = {TestGroups.REGRESSION, TestGroups.SANITY, TestGroups.LOGIN})
public class SuccessfulLoginLogout extends BaseTest {

    private WebDriver driver;

    private String sUsername;
    private String sPassword;

    @BeforeMethod
    public void setupTest() {
        driver = setUpDriver();
        sUsername = PropertiesUtils.getAdminUsername();
        sPassword = PropertiesUtils.getAdminPassword();
    }

    @Test
    public void testSuccessfulLoginLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPage();
        loginPage.typeUsername(sUsername);
        loginPage.typePassword(sPassword);
        DevicesPage devicesPage = loginPage.clickOnLoginButton();
        devicesPage.logout();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        tearDown(driver, testResult);
    }
}
