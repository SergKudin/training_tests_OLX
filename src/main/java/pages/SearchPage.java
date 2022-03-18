package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage implements Credentialable {

    private static final String SEARCH_RESULTS_TITLE = "//td[@class='title-cell ']";
    private static final String SEARCH_RESULTS_PRICE = "//td[@class='wwnormal tright td-price']";
    private static final String SEARCH_RESULTS_LINK = SEARCH_RESULTS_TITLE + "//a";

    private static final ArrayList<ResultSearch> ListResultSearch = new ArrayList<>();
    Pagination pagination = new Pagination();

    public SearchPage() {
        super();
        WebUtils.waitUntilElementVisible(homeButton);
    }

    public SearchPage getResultSearchPage() {
        String NameLot;
        String Price;
        String Link;
        List<WebElement> ResultTitle;
        List<WebElement> ResultPrice;
        List<WebElement> ResultLink;
        WebUtils.pause(1000);
        ResultTitle = driver.findElements(By.xpath(SEARCH_RESULTS_TITLE));
        ResultPrice = driver.findElements(By.xpath(SEARCH_RESULTS_PRICE));
        ResultLink = driver.findElements(By.xpath(SEARCH_RESULTS_LINK));
        for (int i = 0; i < ResultTitle.size(); i++) {
            NameLot = ResultTitle.get(i).getText();
            Price = ResultPrice.get(i).getText();
            Link = ResultLink.get(i).getAttribute("href");
            ListResultSearch.add(new ResultSearch(NameLot, Price, Link));
        }
        return Factory.initPage(SearchPage.class);
    }

    public SearchPage getResultSearch() {
        Logger.logInfo("search results page №" + 1);
        getResultSearchPage();
        try {
            for (int i = 2; i <= pagination.getPagesCount(); i++) {
                pagination.goToPageByIndex(i);
                Logger.logInfo("search results page №" + i);
                getResultSearchPage();
            }
        } catch (NoSuchElementException e) {
        }
        return Factory.initPage(SearchPage.class);
    }

    private String dateSave() {
        String nameFile = "output";
        String status = "Saving data to file '" + nameFile + "': error";

        try {
//            SaveToFileCSV SaveToFile = new SaveToFileCSV();
//            SaveToFile.saveDateToFile(ListResultSearch, nameFile);
            SaveToFileXLSX SaveToFile = new SaveToFileXLSX();
            SaveToFile.saveDateToFileXLSX(ListResultSearch, nameFile);
            status = "Saving data to file '" + nameFile + "': OK";

//Chek
            ReadFileXLSX ReadFile = new ReadFileXLSX(nameFile);
            ReadFile.readToList();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    private Integer getSumPrice() {
        Integer Sum = 0;
        for (ResultSearch i : ListResultSearch) {
            Sum += i.getPrice();
        }
        return Sum;
    }

    private BigDecimal getAveragePrice() {
        BigDecimal average = null;
        BigDecimal a = BigDecimal.valueOf(getSumPrice());
        BigDecimal b = BigDecimal.valueOf(ListResultSearch.size());
        if (ListResultSearch.size() != 0) {
            average = a.divide(b, 2, RoundingMode.HALF_UP);
        }
        return average;
    }

    public int getSizePrice() {
        return ListResultSearch.size();
    }

    public SearchPage getData() {
        Logger.logInfo("Sum of prices: " + getSumPrice() + " грн.");
        Logger.logInfo("Average prices: " + getAveragePrice() + " грн.");
        Logger.logInfo("Number of results: " + getSizePrice());
        return Factory.initPage(SearchPage.class);
    }

    public SearchPage dateSaveToFile() {
        Logger.logInfo(dateSave());
        return Factory.initPage(SearchPage.class);
    }

}
