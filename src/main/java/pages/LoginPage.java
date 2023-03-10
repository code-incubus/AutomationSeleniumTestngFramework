package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Locators;

public class LoginPage extends CommonLoggedOutPage {

    private final String LOGIN_PAGE_URL = getPageUrl(PageUrlPaths.LOGIN_PAGE);

    public LoginPage(WebDriver driver) {
        super(driver);
        logger.info("new LoginPage()");
    }

    public LoginPage open() {
        logger.debug("Opening Login page...");
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    public LoginPage verifyLoginPage() {
        waitForUrlChange(LOGIN_PAGE_URL, Time.TIME_SHORTEST);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public void typeUsername(String sUsername) {
        logger.debug("typeUsername(" + sUsername + ")");
        Assert.assertTrue(isUsernameFieldDisplayed(), "Username Text Field is NOT displayed on Login Page!");
        clearAndTypeTextToWebElement(Locators.USERNAME, sUsername);
    }

    public void typePassword(String sPassword) {
        logger.debug("typePassword(" + sPassword + ")");
        Assert.assertTrue(isPasswordFieldDisplayed(), "Password Text Field is NOT displayed on Login Page!");
        clearAndTypeTextToWebElement(Locators.PASSWORD, sPassword);
    }

    public DevicesPage clickOnLoginButton() {
        logger.debug("clickOnLoginButton()");
        clickOnWebElement(Locators.LOGIN_BUTTON);
        DevicesPage devicesPage = new DevicesPage(driver);
        return devicesPage.verifyDevicesPage();
    }

    public String getUsername() {
        logger.debug("getUsername()");
        Assert.assertTrue(isUsernameFieldDisplayed(), "Username Text Field is NOT displayed on Login Page!");
        return getValueFromWebElement(getWebElement(Locators.USERNAME));
    }

    public boolean isUsernameFieldDisplayed() {
        logger.debug("isUsernameFieldDisplayed()");
        return isWebElementDisplayed(Locators.USERNAME);
    }

    public String getPassword() {
        logger.debug("getPassword()");
        Assert.assertTrue(isPasswordFieldDisplayed(), "Password Text Field is NOT displayed on Login Page!");
        return getValueFromWebElement(getWebElement(Locators.PASSWORD));
    }

    public boolean isPasswordFieldDisplayed() {
        logger.debug("isUsernameFieldDisplayed()");
        return isWebElementDisplayed(Locators.PASSWORD);
    }

    public LoginPage clickOnLoginButtonNoProgress() {
        logger.debug("clickOnLoginButtonNoProgress()");
        clickOnWebElement(Locators.LOGIN_BUTTON);
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }

    public DevicesPage login(String sUsername, String sPassword) {
        logger.debug("login(" + sUsername + ", " + sPassword + ")");
        typeUsername(sUsername);
        typePassword(sPassword);
        clickOnLoginButton();
        DevicesPage devicesPage = new DevicesPage(driver);
        return devicesPage.verifyDevicesPage();
    }

    public boolean isErrorLoginMessageDisplayed() {
        logger.debug("isErrorLoginMessageDisplayed()");
        return isWebElementDisplayed(Locators.INVALID_CREDENTIALS_ERROR_BOX);
    }

    public String getErrorLoginMessage() {
        logger.debug("getErrorLoginMessage()");
        Assert.assertTrue(isErrorLoginMessageDisplayed(), "Error message is NOT displayed on Login Page!");
        return getTextFromWebElement(getWebElement(Locators.INVALID_CREDENTIALS_ERROR_BOX));
    }
}
