package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;
import utils.Locators;

public class LoginPage extends CommonLoggedOutPage {

    private final String LOGIN_PAGE_URL = getPageUrl(PageUrlPaths.LOGIN_PAGE);

    public LoginPage(WebDriver driver) {
        super(driver);
        logger.info("new LoginPage()");
    }

    public LoginPage open() {
        logger.info("Opening Login page...");
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    public LoginPage verifyLoginPage() {
        waitForUrlChangeToExactUrl(LOGIN_PAGE_URL, Time.TIME_SHORTEST);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public void typeUsername(String sUsername) {
        logger.debug("typeUsername(" + sUsername + ")");
        clearAndTypeTextToWebElement(Locators.USERNAME, sUsername);
    }

    public void typePassword(String sPassword) {
        logger.debug("typePassword(" + sPassword + ")");
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
        return getValueFromWebElement(getWebElement(Locators.USERNAME));
    }

    public String getPassword() {
        logger.debug("getPassword()");
        return getValueFromWebElement(getWebElement(Locators.PASSWORD));
    }

    public LoginPage clickOnLoginButtonNoProgress() {
        logger.debug("clickOnLoginButtonNoProgress()");
        clickOnWebElement(Locators.LOGIN_BUTTON);
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }

    public void login(String sUsername, String sPassword) {
        typeUsername(sUsername);
        typePassword(sPassword);
        clickOnLoginButton();
    }
}
