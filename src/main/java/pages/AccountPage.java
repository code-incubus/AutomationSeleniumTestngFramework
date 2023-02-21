package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class AccountPage extends CommonLoggedInPage {

    private final String ACCOUNT_PAGE_URL = getPageUrl(PageUrlPaths.ACCOUNT_PAGE);

    public AccountPage(WebDriver driver) {
        super(driver);
        logger.info("new AccountPage()");
    }

    public AccountPage verifyAccountPage() {
        waitForUrlChangeToExactUrl(ACCOUNT_PAGE_URL, Time.TIME_SHORTEST);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
