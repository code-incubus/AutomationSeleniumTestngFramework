package utils;

import org.openqa.selenium.By;

public class Locators {

    public static By USERNAME = By.id("username");
    public static By PASSWORD = By.id("password");
    public static By LOGIN_BUTTON = By.xpath("//button[@class='btn btn-primary btn-loader btn-loader-left']");
    public static By USERS_PAGE = By.xpath("//a[@id='menu-users']");
    public static By ACCOUNT_PAGE = By.xpath("//a[@id='menu-account']");
    public static By PLATFORM_PAGE = By.xpath("//a[@id='menu-platform']");
}
