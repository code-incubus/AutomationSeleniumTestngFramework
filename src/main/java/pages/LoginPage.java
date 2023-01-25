package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

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

    public void verifyLoginPage() {
        waitForUrlChange(LOGIN_PAGE_URL, Time.TIME_SHORT);
    }
}
