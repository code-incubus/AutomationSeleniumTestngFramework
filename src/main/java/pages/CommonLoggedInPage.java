package pages;

import org.openqa.selenium.WebDriver;
import utils.Locators;

public abstract class CommonLoggedInPage extends BasePage {

    public CommonLoggedInPage(WebDriver driver) {
        super(driver);
    }

    public DevicesPage clickOnDevicesPage() {
        DevicesPage devicesPage = new DevicesPage(driver);
        return devicesPage.verifyDevicesPage();
    }

    public UsersPage clickOnUsersPage() {
        clickOnWebElement(Locators.USERS_PAGE);
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    public AccountPage clickOnAccountPage() {
        AccountPage accountPage = new AccountPage(driver);
        return accountPage.verifyAccountPage();
    }

    public PlatformPage clickOnPlatformPage() {
        PlatformPage platformPage = new PlatformPage(driver);
        return platformPage.verifyPlatformPage();
    }
}
