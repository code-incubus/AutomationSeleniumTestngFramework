package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
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

    protected void tearDown(WebDriver driver, ITestResult testResult) {
        String sTestName = testResult.getTestClass().getName();
        logger.info("tearDown(" + sTestName + ")");
        try {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                //take screenshot
                logger.info("Test " + sTestName + " has failed!");
            }
        } catch (AssertionError | Exception e) {
            logger.error("Exception occurred in teardown(" + sTestName + ")! Message: " + e.getMessage());
        } finally {
            quitDriver(driver);
        }
    }
}
