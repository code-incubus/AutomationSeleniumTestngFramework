package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;

public class DevicesPage extends CommonLoggedInPage {

    private final String DEVICES_PAGE_URL = getPageUrl(PageUrlPaths.DEVICES_PAGE);

    public DevicesPage(WebDriver driver) {
        super(driver);
        logger.info("new DevicesPage()");
    }

    public DevicesPage open() {
        logger.debug("Opening Devices page...");
        driver.get(DEVICES_PAGE_URL);
        return this;
    }

    public DevicesPage verifyDevicesPage() {
        waitForUrlChangeToExactUrl(DEVICES_PAGE_URL, Time.TIME_SHORTEST);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }
}
