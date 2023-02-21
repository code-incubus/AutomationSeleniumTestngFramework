package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils extends LoggerUtils {

    private static final String sScreenshotPath = System.getProperty("user.dir") + PropertiesUtils.getScreenshotsFolder();

    private static String createScreenshotPath(String sScreenshotName) {
        return sScreenshotPath + sScreenshotName + ".png";
    }

    public static void takeScreenshot(WebDriver driver, String sTestName) {
        logger.debug("takeScreenshot(" + sTestName + ")");
        String sPathToFile = createScreenshotPath(sTestName);
        if (WebDriverUtils.hasDriverQuit(driver)) {
            logger.debug("Screenshot for test " + sTestName + " could not be taken! Driver instance has quit!");
            return;
        }
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(sPathToFile);
        try {
            FileUtils.copyFile(scrFile, destinationFile);
            logger.debug("Screenshot for test " + sTestName + " successfully saved to " + sPathToFile);
        } catch (IOException e) {
            logger.debug("Screenshot for test " + sTestName + " could not be saved to " + sPathToFile + ". Message: " + e.getMessage());
        }
    }
}
