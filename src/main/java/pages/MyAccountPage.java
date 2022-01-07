package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    private static final String HOME_LOGO = "//a[@id=\"headerLogo\"]";

    @FindBy(xpath = HOME_LOGO)
    private WebElement homeButton;

    public MyAccountPage() {

    }

    public MainPage gotoMainPage() {
        clickElement(homeButton);
        return new MainPage();
    }
}

