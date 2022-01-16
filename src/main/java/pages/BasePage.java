package pages;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Factory;
import utils.WebUtils;

public class BasePage {
    protected WebDriver driver = WebDriverManager.getDriver();
    protected final Logger logger;
    private static final String HOME_LOGO = "//a[@id='headerLogo']";

    @FindBy(xpath = HOME_LOGO)
    WebElement homeButton;

    public BasePage() {
        PageFactory.initElements(driver, this);
        logger = LoggerFactory.getLogger(this.getClass());
        waitUntilPageIsLoaded();

    }

    public void waitUntilPageIsLoaded() {
        WebUtils.waitUntilPageIsLoaded();
    }

    public void clickElement(WebElement element) {
        StringBuilder log = new StringBuilder("Click " + WebUtils.getLocator(element));
        WebUtils.scrollToElement(element);
        WebUtils.pause(100);
        boolean isPrevClickedWasSuccessful = false;
        try {
            element.click();
            isPrevClickedWasSuccessful = true;
        } catch (Exception exSimple) {
            log.append("; Simple click failed; ");
        }
        if (!isPrevClickedWasSuccessful) {
            try {
                log.append("Try click by Actions; ");
                WebUtils.clickActions(element);
                log.append("Click by Actions successful.");
                isPrevClickedWasSuccessful = true;
            } catch (Exception exActions) {
                log.append("Click by Actions failed; ");
            }
        }
        if (!isPrevClickedWasSuccessful) {
            try {
                log.append("Try click by JS; ");
                WebUtils.clickJs(element);
                log.append("Click by JS successful.");
                isPrevClickedWasSuccessful = true;
            } catch (Exception exJx) {
                log.append("Click by JS failed; ");
            }
        }
        if (!isPrevClickedWasSuccessful) {
            try {
                log.append("Try complex click; ");
                WebUtils.complexClick(element);
                log.append("Complex click successful.");
            } catch (Exception exComplex) {
                log.append("Click failed.");
                logger.info(log.toString());
                throw new RuntimeException("click element '" + element + "' failed");
            }
        }
        logger.info(log.toString());
        WebUtils.pause(900);
    }

    public MainPage gotoMainPage() {
        clickElement(homeButton);
        return Factory.initPage(MainPage.class);
//        return new MainPage();
    }
}
