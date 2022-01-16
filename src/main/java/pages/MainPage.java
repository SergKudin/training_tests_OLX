package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainPage  extends BasePage implements Credentialable {

    private static final String LOGIN_PAGE = "//a[@id='topLoginLink']";
    private static final String SEARCH = "//input[@id='headerSearch']";
    private static final String BUTTON_SEARCH = "//input[@id='submit-searchmain']";

    @FindBy(xpath = LOGIN_PAGE)
    private WebElement loginPage;
    @FindBy(xpath = SEARCH)
    private WebElement search;
    @FindBy(xpath = BUTTON_SEARCH)
    private WebElement buttonSearch;

    public MainPage() {
        super();
        WebUtils.waitUntilElementVisible(homeButton);
    }

    public LoginPage goToLoginPage() {
        clickElement(loginPage);
//        driver.findElement(By.xpath(xpLoginPage)).click();
        return Factory.initPage(LoginPage.class);
    }

    public SearchPage search(String request) {
        Logger.logInfo("Search: " + request);
//        driver.findElement(By.xpath(xpSearch)).sendKeys(request);
//        driver.findElement(By.xpath(xpButtonSearch)).submit();
        search.sendKeys(request);
        buttonSearch.submit();
        return new SearchPage(); //return Factory.initPage(SearchPage.class);
    }

}
