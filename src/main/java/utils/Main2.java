package utils;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.Pagination;

public class Main2 {

    // зайти на главную стр. ОЛХ, залогиниться, выполнить поиск по наименованию товара
    // получить результаты поиска, вернуть среднее значение стоимости товара по всем
    // предложениям по Украине для всех страниц результатов поиска


    private static String Address = "https://www.olx.ua";//"https://www.olx.ua/";
    private static String request = "сверлильный станок";

    static WebDriver driver = WebDriverManager.getDriverOld();

    public static void main(String[] args) {

        driver.get(Address);

        MainPage mainPage = Factory.initPage(MainPage.class)
                .goToLoginPage()
                .userLogin()
                .gotoMainPage();

        Logger.logInfo("Search: " + request);
        mainPage.search(request);
        mainPage.getResultSearchPage();
        Logger.logInfo(mainPage.dateSave());
        Logger.logInfo("Sum of prices: " + mainPage.getSumPrice() + " грн.");
        Logger.logInfo("Average prices: " + mainPage.getAveragePrice() + " грн.");
        Logger.logInfo("Number of results: " + mainPage.getSizePrice());

        Pagination pagination = new Pagination();
        Logger.logInfo("" + pagination.isCurrentPage(1));
        pagination.goToPageByIndex(1)
                .goPrevPage()
                .goNextPage()
                .goToPageByIndex(2)
                .goLastPage()
                .goFirstPage();
        Logger.logInfo("" + pagination.isCurrentPage(1));
        Logger.logInfo("" + pagination.isCurrentPage(2));
        Logger.logErr("finished");
        driver.quit();
    }
}