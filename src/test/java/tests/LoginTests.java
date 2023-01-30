package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DevicesPage;
import pages.LoginPage;
import utils.PropertiesUtils;

public class LoginTests extends BaseTest {

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
    public void test01_LOGIN_001_successfulLoginLogout() {
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.open();
        loginPage.verifyLoginPage();
        loginPage.typeUsername(sUsername);
        loginPage.typePassword(sPassword);
        DevicesPage devicesPage = loginPage.clickOnLoginButton();
        devicesPage.clickOnUsersPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        tearDown(driver, testResult);
    }
}
