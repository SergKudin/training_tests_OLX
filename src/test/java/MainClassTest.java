
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.LoginPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class MainClassTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeClass
    public void BeforeClassMethod() {
        String PathDriver = "C:\\jdk\\Project\\Driver\\msedgedriver.exe";
        String BrowserDriver = "webdriver.edge.driver";
        String Address = "https://www.olx.ua";//"https://www.olx.ua/";
        System.setProperty(BrowserDriver, PathDriver);
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Address);
        System.out.println("Driver OK");
        mainPage = new MainPage();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void Test1() {
        LoginPage loginPage = mainPage.goToLoginPage();
        Assert.assertEquals(loginPage,loginPage.userLogin());
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public void afterClassMetod() {
    }


}
