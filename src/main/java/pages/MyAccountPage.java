package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Factory;
import utils.WebUtils;

public class MyAccountPage extends BasePage {

//    private static final String HOME_LOGO = "//a[@id=\"headerLogo\"]";
//
//    @FindBy(xpath = HOME_LOGO)
//    private WebElement homeButton;

    public MyAccountPage() {
        super();
        WebUtils.waitUntilElementVisible(homeButton);
    }


}

