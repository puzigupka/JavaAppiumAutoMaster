import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeWorkTests extends CoreTestCase {



    @Test
    public void testHomeworkCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Apple");
        SearchPageObject.clickByArticleWithSubstring("Apple");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Other";
        ArticlePageObject.addArticleToMyLists(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Apple");
        SearchPageObject.clickByArticleWithSubstring("Apple Inc.");


    }


    @Test
    public void testAssertTitle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Apple");
        SearchPageObject.clickByArticleWithSubstring("Apple");
        String title = "Apple";
//        SearchPageObject.assertTitle(String article_title);
    }

}

