package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Locators;

public abstract class CommonLoggedInPage extends BasePage {

    public CommonLoggedInPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTabDisplayed(By locator) {
        logger.debug("isTabDisplayed(" + locator + ")");
        return isWebElementDisplayed(locator);
    }

    public DevicesPage clickOnDevicesPage() {
        logger.debug("clickOnDevicesPage()");
        Assert.assertTrue(isTabDisplayed(Locators.DEVICES_PAGE), "Devices Tab is NOT displayed on Navigation Bar!");
        DevicesPage devicesPage = new DevicesPage(driver);
        return devicesPage.verifyDevicesPage();
    }

    public UsersPage clickOnUsersPage() {
        logger.debug("clickOnUsersPage()");
        Assert.assertTrue(isTabDisplayed(Locators.USERS_PAGE), "Users Tab is NOT displayed on Navigation Bar!");
        clickOnWebElement(Locators.USERS_PAGE);
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    public AccountPage clickOnAccountPage() {
        logger.debug("clickOnAccountPage()");
        Assert.assertTrue(isTabDisplayed(Locators.ACCOUNT_PAGE), "Account Tab is NOT displayed on Navigation Bar!");
        clickOnWebElement(Locators.ACCOUNT_PAGE);
        AccountPage accountPage = new AccountPage(driver);
        return accountPage.verifyAccountPage();
    }

    public PlatformPage clickOnPlatformPage() {
        logger.debug("clickOnPlatformPage()");
        Assert.assertTrue(isTabDisplayed(Locators.PLATFORM_PAGE), "Platform Tab is NOT displayed on Navigation Bar!");
        clickOnWebElement(Locators.PLATFORM_PAGE);
        PlatformPage platformPage = new PlatformPage(driver);
        return platformPage.verifyPlatformPage();
    }

    public LoginPage logout() {
        logger.debug("logout()");
        clickOnWebElement(Locators.USER_PICTURE);
        clickOnWebElement(Locators.LOGOUT_BUTTON);
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }
}
