package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static String PathDriver = "C:\\jdk\\Project\\Driver\\msedgedriver.exe";
    private static String BrowserDriver = "webdriver.edge.driver";

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) driver = makeDriver();
        return driver;
    }

    private static WebDriver makeDriver() {
        System.setProperty(BrowserDriver, PathDriver);
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
