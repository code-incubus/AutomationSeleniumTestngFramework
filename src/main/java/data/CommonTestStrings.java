package data;

import org.testng.Assert;
import utils.PropertiesUtils;

import java.util.Properties;

public final class CommonTestStrings {

    public static final String sLocaleFile = "locale_" + PropertiesUtils.getLocale() + ".loc";
    public static final String sLocalePath = "\\locale\\" + sLocaleFile;

    public static final Properties locale = PropertiesUtils.loadPropertiesFile(sLocalePath);

    private static String getLocaleString(String title) {
        String text = locale.getProperty(title);
        Assert.assertNotNull(text, "String " + text + " doesn't exist in file " + sLocaleFile + "!");
        return text;
    }

    public static String getLoginErrorMessage() {
        return getLocaleString("INVALID_CREDENTIALS_ERROR_MESSAGE");
    }
}
