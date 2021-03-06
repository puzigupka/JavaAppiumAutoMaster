package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
                SEARCH_INIT_ELEMENT,
                SEARCH_INPUT,
                SEARCH_CANCEL_BUTTON,
                SEARCH_RESULT_BY_SUBSTRING_TPL,
                SEARCH_RESULT_ELEMENT,
                SEARCH_EMPTY_RESULT_ELEMENT,
                SEARCH_RESULT_TITLE_LOCATOR_TPL,
                SKIP_BUTTON;




    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }


    /* TEMPLATES METHODS */
    private  static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultTitleLocatorTpl(String title) {
        return SEARCH_RESULT_TITLE_LOCATOR_TPL.replace("{TITLE}", title);
    }
    /* TEMPLATES METHODS */

    public void skipClick(){
        this.waitForElementAndClick(SKIP_BUTTON,
                "Cannot find 'Skip' button", 5);
    }


    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button!", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present!", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button.", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String subsrting)
    {
        String search_result_xpath = getResultSearchElement(subsrting);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring" + subsrting);
    }

    public void clickForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find search result", 10);
    }

    public void clickByArticleWithSubstring(String subsrting)
    {
        String search_result_xpath = getResultSearchElement(subsrting);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + subsrting, 10);
    }

    public int getAmountOfFoundArticle()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);

    }

    public void assertThereIsNotResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "we supposed not to any results");
    }

//метод для Ex4
    public void assertTitle(String title){
        String search_result_by = getSearchResultTitleLocatorTpl(title);


        this.waitForElementPresent(search_result_by , "Error", 15);

        this.assertElementPresent(search_result_by, "as title article " + title);
    }


    public void assertElementPresent(String locator, String error_message){
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements == 0 ) {
            String default_message = "An element '" + locator + "' cannon found";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
}


