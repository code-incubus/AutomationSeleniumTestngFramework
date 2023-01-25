package tests;

import org.openqa.selenium.WebDriver;
import utils.LoggerUtils;
import utils.WebDriverUtils;

public abstract class BaseTest extends LoggerUtils {

    protected WebDriver setUpDriver() {
        logger.debug("setupDriver()");
        return WebDriverUtils.setupDriver();
    }

    protected void quitDriver(WebDriver driver) {
        logger.debug("quitDriver()");
        WebDriverUtils.quitDriver(driver);
    }

    protected void tearDown(WebDriver driver) {
        //TODO to be continued...
        logger.debug("tearDown()");
        quitDriver(driver);
    }
}
