package utils;

import core.Timeouts;
import core.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebUtils {
    private static WebDriver driver = WebDriverManager.getDriver();
    private static JavascriptExecutor jse = (JavascriptExecutor) driver;
    private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);

    public static void setDriver(WebDriver d) {
        driver = d;
        jse = (JavascriptExecutor) driver;
    }

    public static boolean isElementPresent(String path) {
        //https://stackoverflow.com/questions/12270092/best-way-to-check-that-element-is-not-present-using-selenium-webdriver-with-java
        List<WebElement> ec = driver.findElements(By.xpath(path));
        return ec.size() > 0;
    }

    public static WebElement getElement(String path) {
        return driver.findElement(By.xpath(path));
    }

    public static WebElement scrollToElement(WebElement element) {
        jse.executeScript("arguments[0].scrollIntoView()", element);
        return element;
    }

    public static void click(WebElement e) {
        getClickWait().until(driver -> {
            e.click();
            return true;
        });
    }

    public static void complexClick(String path) {
        getClickWait().until(driver -> {
            WebElement e = driver.findElement(By.xpath(path));
            scrollToElement(e);
            e.click();
            return true;
        });
    }

    private static FluentWait<WebDriver> getClickWait() {
        return new WebDriverWait(driver, Timeouts.CLICK_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public static void clickActions(WebElement element) {
        new Actions(driver).moveToElement(element).click(element).build().perform();
    }

    public static void complexClick(WebElement e) {
        waitUntilPageIsLoaded();
        getClickWait().withMessage("Click failed").until(driver -> {
            scrollToElement(e);
            clickJs(e);
            return true;
        });
    }

    public static void clickJs(WebElement element) {
        jse.executeScript("arguments[0].click()", element);
    }

    public static String getLocator(WebElement e) {
        String locator = ("" + e).split(" -> ")[1].split(": ")[1];
        return String.format("\"%s\"", locator.substring(0, locator.length() - 1));
    }

    public static void waitUntilPageIsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Timeouts.PAGE_LOADING_TIMEOUT);
        wait.until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public static void saveScreen(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targetPath = FileUtils.SCREENSHOTS_DIR_PATH
                + FileSystems.getDefault().getSeparator() + fileName
                + getTimeStamp("_yyyy.MM.dd_HH.mm.ss")
                + ".png";
        logger.info("saved screen: " + targetPath);
        FileUtils.copy(srcFile.toPath(), Paths.get(targetPath));
    }

    public static String getTimeStamp(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);  //почитать
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static WebElement waitUntilElementVisible(WebElement e) {
        FluentWait<WebDriver> wait = new WebDriverWait(driver, Timeouts.ELEMENT_VISIBILITY_TIMEOUT)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(e));
        return e;
    }
}
