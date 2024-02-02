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

        String browser = PropertiesUtils.getBrowser();
        boolean isRemote = PropertiesUtils.getRemote();
        boolean isHeadless = PropertiesUtils.getHeadless();
        String hubUrl = PropertiesUtils.getHubUrl();

        logger.info("setUpDriver(" + browser + ", Is Headless: " + isHeadless + ")");

        try {
            if (isRemote) {
                driver = createRemoteDriver(browser, isHeadless, hubUrl);
            } else {
                driver = createLocalDriver(browser, isHeadless);
                driver.get(PropertiesUtils.getBaseUrl());
            }
        } catch (MalformedURLException e) {
            Assert.fail("Cannot create driver! Path to browser '" + browser + "' driver is not correct!");
        }

        setDriverTimeouts(driver);
        maximizeWindow(driver);
        return driver;
    }

    private static WebDriver createRemoteDriver(String browser, boolean isHeadless, String hubUrl) throws MalformedURLException {
        RemoteWebDriver remoteWebDriver;

        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                remoteWebDriver = new RemoteWebDriver(new URL(hubUrl), firefoxOptions);
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                }
                remoteWebDriver = new RemoteWebDriver(new URL(hubUrl), chromeOptions);
                break;
            default:
                Assert.fail("Cannot create driver! Browser '" + browser + "' is not recognized!");
                return null; // Unreachable, but necessary for compiler
        }

        remoteWebDriver.setFileDetector(new LocalFileDetector());
        return remoteWebDriver;
    }


    private static WebDriver createLocalDriver(String browser, boolean isHeadless) {
        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                return new FirefoxDriver(firefoxOptions);
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                }
                return new ChromeDriver(chromeOptions);
            default:
                Assert.fail("Cannot create driver! Browser '" + browser + "' is not recognized!");
                return null; // Unreachable, but necessary for compiler
        }
    }

    private static void setDriverTimeouts(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Time.IMPLICIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Time.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Time.ASYNC_SCRIPT_TIMEOUT));
    }

    private static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null && !hasDriverQuit(driver)) {
            driver.quit();
        }
    }

    public static boolean hasDriverQuit(WebDriver driver) {
        return driver == null || ((RemoteWebDriver) driver).getSessionId() == null;
    }
}
