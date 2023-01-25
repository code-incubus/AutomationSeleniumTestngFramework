package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LoggerUtils;
import utils.PropertiesUtils;
import utils.WebDriverUtils;

import java.time.Duration;

public abstract class BasePage extends LoggerUtils {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        Assert.assertFalse(WebDriverUtils.hasDriverQuit(driver), "Driver instance has quit!");
        this.driver = driver;
    }

    protected String getPageUrl(String sPath) {
        logger.info("getPageUrl(" + sPath + ")");
        return PropertiesUtils.getBaseUrl() + sPath;
    }

    protected boolean waitForUrlChange(String sUrl, int iTimeouts) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(iTimeouts));
        return driverWait.until(ExpectedConditions.urlContains(sUrl));
    }

    protected boolean waitForUrlChangeToExactUrl(String sUrl, int iTimeouts) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(iTimeouts));
        return driverWait.until(ExpectedConditions.urlToBe(sUrl));
    }

    protected boolean waitUntilPageIsReady(int timeout) {
        logger.info("waitUntilPageIsReady(" + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    protected WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    protected WebElement getWebElement(By locator, int timeout) {
        logger.debug("getWebElement(" + locator + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void clickOnWebElement(By locator) {
        logger.debug("clickOnWebElement(" + locator + ")");
        WebElement button = getWebElement(locator);
        button.click();
    }

    protected void typeTextToWebElement(By locator, String text) {
        logger.debug("getWebElement(" + locator + ", " + text + ")");
        WebElement element = getWebElement(locator);
        element.sendKeys(text);
    }

    protected void clearAndTypeTextToWebElement(By locator, String text) {
        logger.debug("getWebElement(" + locator + ", " + text + ")");
        WebElement element = getWebElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getAttributeFromWebElement(WebElement element, String sAttribute) {
        logger.debug("getAttributeFromWebElement(" + element + ", " + sAttribute + ")");
        return element.getAttribute(sAttribute);
    }

    protected String getValueFromWebElement(WebElement element) {
        logger.debug("getValueFromWebElement(" + element + ")");
        return getAttributeFromWebElement(element, "value");
    }
}
