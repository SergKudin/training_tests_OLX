import org.testng.annotations.Test;
import pages.MainPage;
import pages.Pagination;
import utils.Factory;
import utils.Logger;

public class MainClassTest extends BaseTest {

    @Test
    public void Test1() {

        driver.get(Address);

        MainPage mainPage = Factory.initPage(MainPage.class)
                .goToLoginPage()
                .userLogin()
                .gotoMainPage();

        Logger.logInfo("Search: " + request);
        mainPage.search(request)
                .getResultSearch();
        Logger.logInfo(mainPage.dateSave());
        Logger.logInfo("Sum of prices: " + mainPage.getSumPrice() + " грн.");
        Logger.logInfo("Average prices: " + mainPage.getAveragePrice() + " грн.");
        Logger.logInfo("Number of results: " + mainPage.getSizePrice());

//        Pagination pagination = new Pagination();
//        Logger.logInfo("" + pagination.isCurrentPage(1));
//        pagination.goToPageByIndex(1)
//                .goPrevPage()
////                .goNextPage()
//                .goToPageByIndex(2)
//                .goLastPage()
//                .goFirstPage();
//        Logger.logInfo("" + pagination.isCurrentPage(1));
//        Logger.logInfo("" + pagination.isCurrentPage(2));
//        Logger.logErr("finished");

    }
}
