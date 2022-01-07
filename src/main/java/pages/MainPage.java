package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Factory;
import utils.ResultSearch;
import utils.SaveToFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class MainPage extends BasePage {

    private static String xpLoginPage = "//a[@id=\"topLoginLink\"]";
    private static String xpSearch = "//input[@id=\"headerSearch\"]";
    private static String xpButtonSearch = "//input[@id=\"submit-searchmain\"]";
    private static String xpSearchPages = "//span[@class='item fleft']";
    private static String xpSearchPagesBlock = "//span[@class=\"block fleft\"]";
    private static String xpSearchResults = "//tr[@class=\"wrap\"]";
    private static String xpSearchResultsTitle = "//td[@class=\"title-cell \"]";
    private static String xpSearchResultsPrice = "//td[@class=\"wwnormal tright td-price\"]";

    private static HashMap<Integer, WebElement> SearchPages = new HashMap();
    private static ArrayList<ResultSearch> ListResultSearch = new ArrayList<>();

    public MainPage() {
    }

    public LoginPage goToLoginPage() {
        driver.findElement(By.xpath(xpLoginPage)).click();
        return Factory.initPage(LoginPage.class);
    }

    public MainPage search(String request) {
        driver.findElement(By.xpath(xpSearch)).sendKeys(request);
        driver.findElement(By.xpath(xpButtonSearch)).submit();
        return Factory.initPage(MainPage.class);
    }

    public void getResultSearchPage() {
        String NameLot;
        String Price;
        List<WebElement> ResultTitle;
        List<WebElement> ResultPrice;
        ResultTitle = driver.findElements(By.xpath(xpSearchResultsTitle));
        ResultPrice = driver.findElements(By.xpath(xpSearchResultsPrice));
        for (int i = 0; i < ResultTitle.size(); i++) {
            System.out.print(i);
            NameLot = ResultTitle.get(i).getText();
            Price = ResultPrice.get(i).getText();
            System.out.print(". " + NameLot);
            System.out.println("; Price = " + Price);
            ListResultSearch.add(new ResultSearch(NameLot, Price));
        }
    }

    public String dateSave() {
        String nameFile = "output.csv";
        String status = "Saving data to file \"" + nameFile + "\": error";;
        try {
            SaveToFile SaveToFile = new SaveToFile();
            SaveToFile.saveDateToFile(ListResultSearch, nameFile);
            status = "Saving data to file \"" + nameFile + "\": OK";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    public Integer getSumPrice() {
        Integer Sum = 0;
        for (ResultSearch i : ListResultSearch) {
            Sum += i.getPrice();
        }
        return Sum;
    }

    public BigDecimal getAveragePrice() {
        BigDecimal average = null;
        BigDecimal a = BigDecimal.valueOf(getSumPrice());
        BigDecimal b = BigDecimal.valueOf(ListResultSearch.size());
        if (ListResultSearch.size()!=0) {
            average = a.divide(b, 2, RoundingMode.HALF_UP);
        }
        return average;
    }

    public int getSizePrice() {
        return ListResultSearch.size();
    }
}
