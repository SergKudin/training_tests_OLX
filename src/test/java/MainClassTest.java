import org.testng.annotations.Test;
import pages.MainPage;
import pages.Pagination;
import pages.SearchPage;
import utils.Factory;
import utils.Logger;

public class MainClassTest extends BaseTest {

    @Test
    public void Test1() {
        Logger.logInfo("Start Test1");

        MainPage mainPage = Factory.initPage(MainPage.class)
//                .goToLoginPage()
//                .userLogin()
//                .gotoMainPage()
                .search(request)
                .getResultSearch()
                .getData()
                .dateSaveToFile()
                .gotoMainPage();

//        Pagination pagination = new Pagination();
//        Logger.logInfo("" + pagination.isCurrentPage(1));
//        pagination.goToPageByIndex(1)
//                .goPrevPage()
//                .goNextPage()
//                .goToPageByIndex(2)
//                .goLastPage()
//                .goFirstPage();
//        Logger.logInfo("" + pagination.isCurrentPage(1));
//        Logger.logInfo("" + pagination.isCurrentPage(2));
//        Logger.logErr("finished");

    }
}
