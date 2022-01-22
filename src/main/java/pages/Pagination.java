package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebUtils;

public class Pagination extends BasePage {
    private final String BASE = "//div[@class = 'pager rel clr']";
    private final String PREV_PAGE_BTN = BASE + "//*[@data-cy = 'page-link-prev']";
    private final String NUMBERED_PAGE_BTN = BASE + "//*[@data-cy = 'page-link-%d']";
    private final String NEXT_PAGE_BTN = BASE + "//*[@data-cy = 'page-link-next']";
    private final String LAST_PAGE_BTN = BASE + "//*[@data-cy = 'page-link-last']";
    //    private final String LAST_PAGE_BTN = BASE + "//*[@data-cy = 'page-link-next']//ancestor::span//preceding-sibling::span[position() = 1]";
    private final String LAST_PAGE_NUMBER = LAST_PAGE_BTN + "//span";
    private final String CURRENT_PAGE_NUMBER = BASE + "//span[@data-cy = 'page-link-current']";
    private final String FIRST_PAGE_BTN = BASE + "//*[@data-cy = 'page-link-prev']//ancestor::span//following-sibling::span[position() = 1]";

    private int pageCount = -1;

    @FindBy(xpath = BASE)
    WebElement base;

    @FindBy(xpath = PREV_PAGE_BTN)
    WebElement previousPageButton;

    @FindBy(xpath = NUMBERED_PAGE_BTN)
    WebElement numberedPageButton;

    @FindBy(xpath = NEXT_PAGE_BTN)
    WebElement nextPageButton;

    @FindBy(xpath = LAST_PAGE_BTN)
    WebElement lastPageButton;

    @FindBy(xpath = LAST_PAGE_NUMBER)
    WebElement lastPageNumber;

    @FindBy(xpath = CURRENT_PAGE_NUMBER)
    WebElement currentPageNumber;

    @FindBy(xpath = FIRST_PAGE_BTN)
    WebElement firstPageButton;

    public Pagination() {

    }

    public Pagination goFirstPage() {
        if (getCurrentPageNumber() != 1) {
            waitLoadedAndScrollToItself();
            clickElement(firstPageButton);
        }
        return this;
    }

    public Pagination goPrevPage() {
        if (getCurrentPageNumber() != 1) {
            waitLoadedAndScrollToItself();
            clickElement(previousPageButton);
        }
        return this;
    }

    public Pagination goToPageByIndex(int index) {
        validateIndex(index);
        if (getCurrentPageNumber() == index) return this;
        if (getPagesCount() == index) {
            goLastPage();
            waitLoadedAndScrollToItself();
            return this;
        }
        if (!isPageButtonVisible(index)) {
            if (getCurrentPageNumber() < index) {
                while (!isPageButtonVisible(index)) {
                    goNextPage();
                    waitLoadedAndScrollToItself();
                }
            } else {
                while (!isPageButtonVisible(index)) {
                    goPrevPage();
                    waitLoadedAndScrollToItself();
                }
            }
        }
        clickElement(WebUtils.getElement(getPageBtnPath(index)));
        waitLoadedAndScrollToItself();
        return this;
    }

    public Pagination goNextPage() {
        if (getCurrentPageNumber() != getPagesCount()) {
            clickElement(nextPageButton);
            waitLoadedAndScrollToItself();
        }
        return this;
    }

    public Pagination goLastPage() {
        if (getCurrentPageNumber() != getPagesCount()) {
            clickElement(lastPageButton);
            waitLoadedAndScrollToItself();
        }
        return this;
    }

    public int getPagesCount() {
        return (pageCount == -1)
                ? pageCount = Integer.parseInt(lastPageNumber.getAttribute("innerText"))
                : pageCount;
    }

    public boolean isCurrentPage(int index) {
        validateIndex(index);
        return index == getCurrentPageNumber();
    }

    public Pagination scrollToItself() {
        WebUtils.scrollToElement(base);
        return this;
    }

    private boolean isPageButtonVisible(int index) {
        validateIndex(index);
        return WebUtils.isElementPresent(getPageBtnPath(index));
    }

    private void validateIndex(int index) {
        if (index < 1 | index > getPagesCount())
            throw new IllegalArgumentException("Page index should be between 1 and last index = " + getPagesCount());
    }

    private String getPageBtnPath(int index) {
        return String.format(NUMBERED_PAGE_BTN, index);
    }

    private int getCurrentPageNumber() {
        WebUtils.waitUntilElementVisible(currentPageNumber);
        return Integer.parseInt(currentPageNumber.getAttribute("innerText"));
    }

    public int getLastPageNumber() {
        return Integer.parseInt(lastPageNumber.getAttribute("innerText"));
    }

    private Pagination waitLoadedAndScrollToItself() {
        waitUntilPageIsLoaded();
        scrollToItself();
        return this;
    }


}
