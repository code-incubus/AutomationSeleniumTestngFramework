package utils;

import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils extends LoggerUtils {


    private static final String sPropertyPath = "common.properties";

    private static final Properties properties = loadPropertiesFile();

    public static Properties loadPropertiesFile(String sFilePath) {
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(sFilePath);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            Assert.fail("Cannot load " + sFilePath + " file! Message: " + e.getMessage());
        }
        return properties;
    }

    private static Properties loadPropertiesFile() {
        return loadPropertiesFile(sPropertyPath);
    }

    private static String getProperty(String sProperty) {
        String sResult = properties.getProperty(sProperty);
        Assert.assertNotNull(sResult, "Cannot find property '" + sProperty + "' in " + sPropertyPath + " file!");
        return sResult;
    }

    private static String getEnvironment() {
        return getProperty("environment");
    }

    private static String getTestBaseUrl() {
        return getProperty("testBaseUrl");
    }

    private static String getLocalBaseUrl() {
        return getProperty("localBaseUrl");
    }

    private static String getProductionBaseUrl() {
        return getProperty("productionBaseUrl");
    }

    public static String getBaseUrl() {
        String sEnvironment = getEnvironment().toLowerCase();
        String sBaseUrl = null;
        switch (sEnvironment) {
            case "local": {
                sBaseUrl = getLocalBaseUrl();
                break;
            }
            case "test": {
                sBaseUrl = getTestBaseUrl();
                break;
            }
            case "prod": {
                sBaseUrl = getProductionBaseUrl();
                break;
            }
            default: {
                Assert.fail("Cannot get BaseUrl! '" + sEnvironment + "' is not recognized!");
            }
        }
        return sBaseUrl;
    }

    public static String getAdminUsername() {
        return getProperty("adminUsername");
    }

    public static String getAdminPassword() {
        return getProperty("adminPassword");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static boolean getRemote() {
        return Boolean.parseBoolean(getProperty("remote"));
    }

    public static boolean getHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public static String getHubUrl() {
        return getProperty("remoteHubUrl");
    }
}
