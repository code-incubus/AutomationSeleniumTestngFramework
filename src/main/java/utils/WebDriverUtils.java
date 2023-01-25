package utils;

import data.Time;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebDriverUtils extends LoggerUtils {

    public static WebDriver setupDriver() {
        WebDriver driver = null;

        String sBrowser = PropertiesUtils.getBrowser();
        boolean bRemote = PropertiesUtils.getRemote();
        boolean bHeadless = PropertiesUtils.getHeadless();
        String sHubUrl = PropertiesUtils.getHubUrl();

        logger.debug("setUpDriver(" + sBrowser + ", Is Headless: " + bHeadless + ")");

        try {
            switch (sBrowser) {
                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(bHeadless);
                    if (bRemote) {
                        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(sHubUrl), options);
                        remoteWebDriver.setFileDetector(new LocalFileDetector());
                        driver = remoteWebDriver;
                    } else {
                        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver-windows-32bit.exe");
                        driver = new FirefoxDriver(options);
                    }
                    break;
                }
                case "chrome": {
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(bHeadless);
                    if (bRemote) {
                        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(sHubUrl), options);
                        remoteWebDriver.setFileDetector(new LocalFileDetector());
                        driver = remoteWebDriver;
                    } else {
                        System.setProperty("webdriver.gecko.driver", "drivers/chromedriver-windows-32bit.exe");
                        driver = new ChromeDriver(options);
                    }
                    break;
                }
                default: {
                    Assert.fail("Cannot create driver1 Browser '" + sBrowser + "' is not recognized!");
                }
            }
        } catch (MalformedURLException e) {
            Assert.fail("Cannot create driver! Path to browser '" + sBrowser + "' driver is not correct!");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Time.IMPLICIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Time.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Time.ASYNC_SCRIPT_TIMEOUT));

        driver.manage().window().maximize();
        return driver;
    }

    public static boolean hasDriverQuit(WebDriver driver) {
        if (driver != null) {
            return ((RemoteWebDriver) driver).getSessionId() == null;
        } else {
            return true;
        }
    }

    public static void quitDriver(WebDriver driver) {
        if (!hasDriverQuit(driver)) {
            driver.quit();
        }
    }
}
