package utils;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.MyAccountPage;
import pages.Pagination;

public class Main2 {

    // зайти на главную стр. ОЛХ, залогиниться, выполнить поиск по наименованию товара
    // получить результаты поиска, вернуть среднее значение стоимости товара по всем
    // предложениям по Украине для всех страниц результатов поиска


    private static String Address = "https://www.olx.ua";//"https://www.olx.ua/";
    private static String request = "сверлильный станок";

    static WebDriver driver= WebDriverManager.getDriver();

    public static void main(String[] args) {

        driver.get(Address);
        System.out.println("Driver OK");
        MainPage mainPage = Factory.initPage(MainPage.class)
                .goToLoginPage()
                .userLogin()
                .gotoMainPage();

        System.out.println("Search: "+ request);
        mainPage.search(request);
        mainPage.getResultSearchPage();
        System.out.println(mainPage.dateSave());
        System.out.println("Sum of prices: " + mainPage.getSumPrice() + " грн.");
        System.out.println("Average prices: " + mainPage.getAveragePrice() + " грн.");
        System.out.println("Number of results: " + mainPage.getSizePrice());

        Pagination pagination = new Pagination();
        System.out.println(pagination.isCurrentPage(1));
        pagination.goToPageByIndex(1);
        pagination.goPrevPage();
        pagination.goNextPage();
        pagination.goToPageByIndex(2);
        pagination.goLastPage();
        pagination.goFirstPage();
        System.out.println(pagination.isCurrentPage(1));
        System.out.println(pagination.isCurrentPage(2));
//
//        driver.quit();
    }
}