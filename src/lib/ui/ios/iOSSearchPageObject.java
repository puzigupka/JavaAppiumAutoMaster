package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "id:Clear text";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
        SEARCH_RESULT_TITLE_LOCATOR_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        SKIP_BUTTON = "id:Skip";
    }
    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
