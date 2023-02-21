package utils;

import org.openqa.selenium.By;

public class Locators {
    public static By USERNAME = By.id("username");
    public static By PASSWORD = By.id("password");
    public static By LOGIN_BUTTON = By.xpath("//button[@class='btn btn-primary btn-loader btn-loader-left']");
    public static By USERS_PAGE = By.xpath("//a[@id='menu-users']");
    public static By ACCOUNT_PAGE = By.xpath("//a[@id='menu-account']");
    public static By PLATFORM_PAGE = By.xpath("//a[@id='menu-platform']");
    public static By USER_PICTURE = By.xpath("//div[@class='navbar-switcher-container']//button[@id='user-profile-dropdown']//following-sibling::span[normalize-space(text())='acme_petrovicDusan']");
    public static By LOGOUT_BUTTON = By.xpath("//div[@class='navbar-switcher-container']//div[@class='dropdown-menu dropdown-menu-right show']//button[text()='Logout']");
    public static By INVALID_CREDENTIALS_ERROR_BOX = By.xpath("//div[@class='form-signin-panel']//p[@class='alert alert-danger']");
}
