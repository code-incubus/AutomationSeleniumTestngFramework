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

    private String sTestName = this.getClass().getName();
    private WebDriver driver;

    private String sUsername;
    private String sPassword;

    @BeforeMethod
    public void setupTest() {
        logger.info("[SETUP TEST] " + sTestName);
        driver = setUpDriver();
        sUsername = PropertiesUtils.getAdminUsername();
        sPassword = PropertiesUtils.getAdminPassword();
    }

    @Test
    public void testSuccessfulLoginLogout() {
        logger.info("[START TEST] " + sTestName);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPage();
        loginPage.typeUsername(sUsername);
        loginPage.typePassword(sPassword);
        DevicesPage devicesPage = loginPage.clickOnLoginButton();
        //devicesPage.verifyDevicesPage(); // TODO This is duplication considering that we already return verified object in previous step.
        devicesPage.logout();
        //loginPage = loginPage.verifyLoginPage(); // TODO This is duplication considering that we already return verified object in previous step.
        devicesPage = loginPage.login(sUsername, sPassword);
        devicesPage.clickOnDevicesPage();
        devicesPage.clickOnUsersPage();
        devicesPage.clickOnAccountPage();
        devicesPage.clickOnPlatformPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        logger.info("[END TEST] " + sTestName);
        tearDown(driver, testResult);
    }
}
