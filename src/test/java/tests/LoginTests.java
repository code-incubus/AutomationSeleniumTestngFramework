package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void test01_LOGIN_001_successfulLoginLogout() {
        WebDriver driver = setUpDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.verifyLoginPage();
        tearDown(driver);
    }
}
