package pages;

import org.openqa.selenium.By;
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

    public void click(By locatorButton) {
        WebElement button = driver.findElement(locatorButton);
        button.click();
    }

    public void type(By locatorType, String text) {
        WebElement element = driver.findElement(locatorType);
        element.sendKeys(text);
    }
}
