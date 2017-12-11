package news.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryTest {
    
    Category category;
    List<Article> articles;
    
    public CategoryTest() {
    }

    @Before
    public void setUp() {
        articles = new ArrayList<>();
        articles.add(new Article("test", "testCaption", null, "testContent",
                "events", null));
        
        category = new Category();
        category.setArticles(articles);
        category.setName("events");
    }
    
    @Test
    public void testGetName() {
        assertEquals("events", category.getName());
    }

    @Test
    public void testSetName() {
        category.setName("weather");
        assertEquals("weather", category.getName());
    }

    @Test
    public void testGetArticles() {
        List<Article> expArticles = new ArrayList<>();
        expArticles.add(new Article("test", "testCaption", null, "testContent",
                "events", null));
        assertEquals(expArticles, category.getArticles());
    }

    @Test
    public void testSetArticles() {
        List<Article> newArticles = new ArrayList<>();
        newArticles.add(new Article("newTest", "newTestCaption", null, 
                "newTestContent", "newEvents", null));
        category.setArticles(newArticles);
        assertEquals(newArticles, category.getArticles());
    }

}
