package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class UsersPage extends CommonLoggedInPage {

    private final String USERS_PAGE_URL = getPageUrl(PageUrlPaths.USERS_PAGE);


    public UsersPage(WebDriver driver) {
        super(driver);
        logger.info("new UsersPage()");
    }

    public UsersPage verifyUsersPage() {
        waitForUrlChangeToExactUrl(USERS_PAGE_URL, Time.TIME_SHORTEST);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
