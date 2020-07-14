package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

public abstract class ArticlePageObject extends MainPageObject
{
    protected static String
                TITLE,
                FOOTER_ELEMENT,
                OPTIONS_BUTTON,
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                ADD_TO_MY_LIST_OVERLAY,
                MY_LIST_NAME_INPUT,
                MY_LIST_OK_BUTTON,
                CLOSE_ARTICLE_BUTTON,
                ARTICLE_SUBTITLE,
                CLOSE_BUTTON_FOR_SYNC_YOUR_SAVED_ARTICLES_POP_UP;



    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeUpToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else {
            this.swipeUpTitleElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    public void addFirstArticleToMyLists(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find options to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK but",
                5
        );
    }

    public void addArticlesToMySaved()
    {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article,cannot find X link",
                15
        );

    }

    public void assertSubtitlePresent(){
        this.assertElementPresent(ARTICLE_SUBTITLE, "We don't found subtitle element");
    }

    public void closeSyncYourSavedArticlesPopUp()
    {
        this.waitForElementAndClick(
                CLOSE_BUTTON_FOR_SYNC_YOUR_SAVED_ARTICLES_POP_UP,
                "Cannot find 'Sync Your Saved Articles' pop-up close button",
                5
        );
    }
}
