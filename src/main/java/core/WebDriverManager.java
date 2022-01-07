package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.FileUtils;
import utils.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static String PathDriver = "C:\\jdk\\Project\\Driver\\msedgedriver.exe";
    private static String BrowserDriver = "webdriver.edge.driver";

    private static WebDriver driver;
    private static EdgeOptions options;

    static {
        options = new EdgeOptions();
    }
    private static Map<String, Object> preferences;

    static {
        preferences = new HashMap<>();
        preferences.put("profile.default_content_setting_values.notifications", 2);
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", FileUtils.DOWNLOADS_PATH.toString());
        preferences.put("directory_upgrade", true);
        preferences.put("safebrowsing.enabled", "false");
    }

    public static WebDriver setDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                ChromeOptions cOptions = new ChromeOptions()
                        .setExperimentalOption("prefs", preferences)
                        .addArguments("--start-maximized")
                        .addArguments("--incognito");
                driver = new ChromeDriver(cOptions);
                break;


            case "chrome-headless":
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions()
                        .setExperimentalOption("prefs", preferences)
                        .addArguments("--proxy-server='direct://'")
                        .addArguments("--proxy-bypass-list=*")
                        .addArguments("--window-size=1920,1080")
                        .addArguments("--headless")
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-dev-shm-usage")
                        .addArguments("--enable-automation")
                        .addArguments("--disable-infobars")
                        .addArguments("--disable-browser-side-navigation")
                        .addArguments("--disable-gpu");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "safari":
                System.setProperty("webdriver.safari.driver", "src/main/resources/drivers/safaridriver");
                driver = new SafariDriver();
                break;

            case "ie":
                io.github.bonigarcia.wdm.WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            case "edge":
                io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "firefox":
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setAcceptInsecureCerts(true);
                options.addPreference("dom.webnotifications.enabled", false);
                options.addPreference("dom.push.enabled", false);
                driver = new FirefoxDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Please specify valid browser name. Valid browser names are: firefox, chrome,chrome-headless, ie ,edge");

        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver getDriverOld() {
        if (driver == null) driver = makeDriver();
        return driver;
    }

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser");
        if (driver == null) driver = setDriver(browser);
        return driver;
    }

    private static WebDriver makeDriver() {
        System.setProperty(BrowserDriver, PathDriver);
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        Logger.logInfo("Driver OK");
        return driver;
    }
}
