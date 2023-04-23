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
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver1 -> driver.findElement(locator));
    }

    /**
     * When concatenating and searching for elements using XPAH, when searching for nested elements, it is very important
     * to take care that the searched element, i.e. nested, does not continue with the (//) character. Why?
     * When we search for an element inside an element (nested), for example, element.findElement(//<xpath>)
     * we make a big mistake because this XPATH searches from the beginning of the DOM structure and not from the current node.
     * For this reason, we need to use (.//) instead of (//) when creating the XPATH
     * @param element
     * @param locator
     * @return
     */
    protected WebElement getNestedWebElement(WebElement element, By locator) {
        logger.debug("getNestedWebElement(" + element + ", " + locator + ")");
        return element.findElement(locator);
    }

    protected WebElement getNestedWebElement(WebElement element, By locator, int timeout) {
        logger.debug("getWebElement(" + element + ", " + locator + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));

    }

    protected void clickOnWebElement(By locator) {
        logger.debug("clickOnWebElement(" + locator + ")");
        WebElement element = getWebElement(locator);
        element.click();
    }

    protected void clickOnWebElement(WebElement element, int timeout) {
        logger.debug("getWebElement(" + element + ", " + timeout + ")");
        WebElement webElement = waitForElementToBeClickable(element, timeout);
        webElement.click();
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

    protected boolean isNestedWebElementDisplayed(WebElement element, By locator) {
        logger.debug("isNestedWebElementDisplayed(" + element + ", " + locator + ")");
        try {
            WebElement nestedElement = element.findElement(locator);
            return nestedElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getTextFromWebElement(WebElement element) {
        logger.debug("getTextFromWebElement(" + element + ")");
        return element.getText();
    }

    protected WebElement waitForElementToBeClickable(WebElement element, int timeout) {
        logger.debug("waitForElementToBeClickable(" + element + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Checking if web element exists via locator. If exist it will return WebElement.
     * Useful without Page Factory.
     *
     * @param locator
     * @param timeout
     * @return
     */
    protected WebElement waitForElementToBeVisible(By locator, int timeout) {
        logger.debug("waitForElementToBeVisible(" + locator + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Checking if web element is visible or not. Here, we already get element.
     * Useful for Page Factory.
     *
     * @param element
     * @param timeout
     * @return
     */
    protected WebElement waitForElementToBeVisible(WebElement element, int timeout) {
        logger.debug("waitForElementToBeVisible(" + element + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean waitForElementToBeInvisible(By locator, int timeout) {
        logger.debug("waitForElementToBeInvisible(" + locator + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitForElementToBeInvisible(WebElement element, int timeout) {
        logger.debug("waitForElementToBeInvisible(" + element + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected boolean isWebElementVisible(By locator, int timeout) {
        logger.debug("isWebElementVisible(" + locator + ", " + timeout + ")");
        try {
            waitForElementToBeVisible(locator, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementVisible(WebElement element, int timeout) {
        logger.debug("isWebElementVisible(" + element + ", " + timeout + ")");
        try {
            waitForElementToBeVisible(element, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementInvisible(By locator, int timeout) {
        logger.debug("isWebElementInvisible(" + locator + ", " + timeout + ")");
        try {
            waitForElementToBeInvisible(locator, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementInvisible(WebElement element, int timeout) {
        logger.debug("isWebElementInvisible(" + element + ", " + timeout + ")");
        try {
            waitForElementToBeInvisible(element, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementEnabled(WebElement element, int timeout) {
        logger.debug("isWebElementEnabled(" + element + ", " + timeout + ")");
        try {
            waitForElementToBeClickable(element, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementEnabled(WebElement element) {
        logger.debug("isWebElementEnabled(" + element + ")");
        return element.isEnabled();
    }
}
