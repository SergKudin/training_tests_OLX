package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class Main {

    // зайти на главную стр. ОЛХ, залогиниться, выполнить поиск по наименованию товара
    // получить результаты поиска, вернуть среднее значение стоимости товара по всем
    // предложениям по Украине для всех страниц результатов поиска


    private static String PathDriver = "C:\\jdk\\Project\\Driver\\msedgedriver.exe";
    private static String BrowserDriver = "webdriver.edge.driver";
    private static String Address = "https://www.olx.ua";//"https://www.olx.ua/";
    private static String Search = "сверлильный станок";

    static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty(BrowserDriver, PathDriver);
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Address);
        System.out.println("Driver OK");
        MainPage mainPage = new MainPage();

//        LoginPage LoginPage = MainPage.clickLogPage();
//        MyAccountPage MyAccountPage = LoginPage.userLogin("0675173446", "Tshda8XS9zRbxMXV4Cm5");
//        System.out.println("Login OK");

//        MainPage = MyAccountPage.gotoMainPage();
        System.out.println("Search: "+Search);
        mainPage.search(Search);
//        mainPage.getResultSearch();
        System.out.println(mainPage.dateSave());
        System.out.println("Sum of prices: " + mainPage.getSumPrice() + " грн.");
        System.out.println("Average prices: " + mainPage.getAveragePrice() + " грн.");
        System.out.println("Number of results: " + mainPage.getSizePrice());

//
//        driver.quit();
    }
}