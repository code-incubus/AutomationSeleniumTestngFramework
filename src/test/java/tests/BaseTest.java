package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import utils.LoggerUtils;
import utils.PropertiesUtils;
import utils.ScreenshotUtils;
import utils.WebDriverUtils;

public abstract class BaseTest extends LoggerUtils {

    protected WebDriver setUpDriver() {
        logger.info("setupDriver()");
        return WebDriverUtils.setupDriver();
    }

    protected void quitDriver(WebDriver driver) {
        logger.info("quitDriver()");
        WebDriverUtils.quitDriver(driver);
    }

    private void ifFailed(WebDriver driver, ITestResult testResult, int session) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            if (PropertiesUtils.getTakeScreenshot()) {
                String sTestName = testResult.getTestClass().getName();
                String sScreenshotName = sTestName;
                if (session > 0) {
                    sScreenshotName = sScreenshotName + "." + session;
                }
                ScreenshotUtils.takeScreenshot(driver, sScreenshotName);
            }
        }
    }

    protected void tearDown(WebDriver driver, ITestResult testResult, int session) {
        String sTestName = testResult.getTestClass().getName();
        logger.debug("tearDown(" + sTestName + ")");
        session = Math.abs(session);
        String sSessionName = sTestName;
        if (session > 0) {
            sSessionName = sSessionName + "." + session;
        }
        logger.debug("tearDown(" + sSessionName + ")");
        try {
            ifFailed(driver, testResult, session);
        } catch (AssertionError | Exception e) {
            logger.error("Exception occurred in teardown(" + sSessionName + ")! Message: " + e.getMessage());
        } finally {
            quitDriver(driver);
        }
    }

    protected void tearDown(WebDriver driver, ITestResult testResult) {
        tearDown(driver, testResult, 0);
    }

    protected void tearDown(WebDriver driver) {
        logger.debug("tearDown()");
        quitDriver(driver);
    }
}
