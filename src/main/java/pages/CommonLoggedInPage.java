package pages;

import org.openqa.selenium.WebDriver;
import utils.Locators;

public abstract class CommonLoggedInPage extends BasePage {

    public CommonLoggedInPage(WebDriver driver) {
        super(driver);
    }

    public DevicesPage clickOnDevicesPage() {
        logger.debug("clickOnDevicesPage()");
        DevicesPage devicesPage = new DevicesPage(driver);
        return devicesPage.verifyDevicesPage();
    }

    public UsersPage clickOnUsersPage() {
        logger.debug("clickOnUsersPage()");
        clickOnWebElement(Locators.USERS_PAGE);
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    public AccountPage clickOnAccountPage() {
        logger.debug("clickOnAccountPage()");
        clickOnWebElement(Locators.ACCOUNT_PAGE);
        AccountPage accountPage = new AccountPage(driver);
        return accountPage.verifyAccountPage();
    }

    public PlatformPage clickOnPlatformPage() {
        logger.debug("clickOnPlatformPage()");
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
