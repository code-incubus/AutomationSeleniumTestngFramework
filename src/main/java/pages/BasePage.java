package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
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
        logger.debug("getPageUrl(" + sPath + ")");
        return PropertiesUtils.getBaseUrl() + sPath;
    }

    protected String getCurrentUrl() {
        logger.debug("getCurrentUrl()");
        return driver.getCurrentUrl();
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
        logger.debug("waitUntilPageIsReady(" + timeout + ")");
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

    protected WebElement getWebElement(By locator, int timeout, int pollingTime) {
        logger.debug("getWebElement(" + locator + ", " + timeout + ", " + pollingTime + ")");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver1 -> driver.findElement(locator));
    }

    protected void clickOnWebElement(By locator) {
        logger.debug("clickOnWebElement(" + locator + ")");
        WebElement element = getWebElement(locator);
        element.click();
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

    protected String getValueFromWebElementJS(WebElement element) {
        logger.debug("getValueFromWebElementJS(" + element + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments.[0].value", element);
    }

    protected boolean isWebElementDisplayed(By locator) {
        logger.debug("isWebElementDisplayed(" + locator + ")");
        try {
            WebElement webElement = getWebElement(locator);
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getTextFromWebElement(WebElement element) {
        logger.debug("getTextFromWebElement(" + element + ")");
        return element.getText();
    }
}
