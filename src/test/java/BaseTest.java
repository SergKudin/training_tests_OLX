import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.Logger;

public class BaseTest {

    protected static String Address = "https://www.olx.ua";//"https://www.olx.ua/";
    protected static String request = "ткань для тафтинга";  //ткань для тафтинга
    protected WebDriver driver;

    @BeforeClass
    public void BeforeClassMethod() {
        Logger.logInfo("Start BeforeClass");
        driver = WebDriverManager.getDriver();
    }

    @BeforeMethod
    public void setUp() {
        Logger.logInfo("Start BeforeMethod");
        driver.get(Address);
    }


    @AfterMethod
    public void tearDown() {
        Logger.logInfo("Start AfterMethod");
        driver.quit();
    }
}
