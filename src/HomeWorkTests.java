import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import lib.Platform;

import java.util.List;

public class HomeWorkTests extends CoreTestCase {



    @Test
    public void testHomeworkCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Apple");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testEx4(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        String search_value = "Java";

        List<WebElement> elements = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement e : elements)
        {
            String search_value_lower_case = search_value.toLowerCase();
            assertTrue("'" + search_value + "' word is not found in search result",
                    e.getText().toLowerCase().contains(search_value_lower_case));
        }
    }

    @Test
    public void testSaveTwoArticleToMyListHomeWork()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Apple");
        SearchPageObject.clickByArticleWithSubstring("Apple");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Other";
        ArticlePageObject.addFirstArticleToMyLists(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Apple");
        SearchPageObject.clickByArticleWithSubstring("Apple Inc.");


    }

    // Ex 6
    @Test
    public void testCheckArticleTitleEx6() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String search_str = "java";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_str);
        searchPageObject.clickForSearchResult("programming language");
        articlePageObject.assertSubtitlePresent();
    }


    @Test
    public void testSaveTwoArticlesToTestListAndDeleteOneArticleEx11()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyLists(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if(Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncYourSavedArticlesPopUp();
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();

        if(Platform.getInstance().isAndroid()) {
            SearchPageObject.typeSearchLine("Java");
        }

        SearchPageObject.clickByArticleWithSubstring("JavaScript");

        ArticlePageObject.waitForTitleElement();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyLists(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
        String second_article_title = MyListsPageObject.getArticleTitle();
        MyListsPageObject.clickArticleByTitle("JavaScript");
        String second_article_title_visible_on_page = ArticlePageObject.getArticleTitle();

        assertEquals("Unexpected title", second_article_title, second_article_title_visible_on_page
        );
    }

}

