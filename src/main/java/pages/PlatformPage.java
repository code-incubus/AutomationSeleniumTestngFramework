package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class PlatformPage extends CommonLoggedInPage {

    private final String PLATFORM_PAGE_URL = getPageUrl(PageUrlPaths.PLATFORM_PAGE);

    public PlatformPage(WebDriver driver) {
        super(driver);
    }

    public PlatformPage verifyPlatformPage() {
        waitForUrlChangeToExactUrl(PLATFORM_PAGE_URL, Time.TIME_SHORTEST);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
