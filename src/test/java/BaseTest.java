import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected static String Address = "https://www.olx.ua";//"https://www.olx.ua/";
    protected static String request = "сверлильный станок";
    protected WebDriver driver;

    @BeforeClass
    public void BeforeClassMethod() {
        driver = WebDriverManager.getDriver();
    }

    @Before
    public void setUp() {
        driver.get(Address);
    }


    @After
    public void tearDown() {
        driver.quit();
    }

    @AfterClass
    public void afterClassMetod() {
    }
}
