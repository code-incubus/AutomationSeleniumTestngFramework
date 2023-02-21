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

    private void ifFailed(WebDriver driver, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            if (PropertiesUtils.getTakeScreenshot()) {
                String sTestName = testResult.getTestClass().getName();
                ScreenshotUtils.takeScreenshot(driver, sTestName);
            }
        }
    }

    protected void tearDown(WebDriver driver, ITestResult testResult) {
        String sTestName = testResult.getTestClass().getName();
        logger.debug("tearDown(" + sTestName + ")");
        try {
            ifFailed(driver, testResult);
        } catch (AssertionError | Exception e) {
            logger.error("Exception occurred in teardown(" + sTestName + ")! Message: " + e.getMessage());
        } finally {
            quitDriver(driver);
        }
    }
}
