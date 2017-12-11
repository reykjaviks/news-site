package news.domain;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleTest {
    
    Article article;
    LocalDateTime pubDate;
    
    public ArticleTest() {
    }
    
    @Before
    public void setUp() {
        pubDate = LocalDateTime.of(2017, 10, 31, 10, 30, 00);
        
        this.article = new Article();
        article.setTitle("Gusty winds");
        article.setCaption("Unsettling weather will continue.");
        article.setContent("All expected.");
        article.setCategory("weather");
        article.setPubDate(pubDate);
    }

    @Test
    public void testGetTitle() {
        assertEquals("Gusty winds", article.getTitle());
    }
    
    @Test
    public void testGetCaption() {
        assertEquals("Unsettling weather will continue.", article.getCaption());
    }

    @Test
    public void testGetFileObject() {
        assertNull(article.getFileObject());
    }

    @Test
    public void testGetContent() {
        assertEquals("All expected.", article.getContent());
    }

    @Test
    public void testGetCategory() {
        assertEquals("weather", article.getCategory());
    }

    @Test
    public void testGetPubDate() {
        LocalDateTime pubDate = LocalDateTime.of(2017, 10, 31, 10, 30, 00);
        assertEquals(pubDate, article.getPubDate());
    }

    @Test
    public void testSetTitle() {
        article.setTitle("New Title");
        assertEquals("New Title", article.getTitle());
    }

    @Test
    public void testSetCaption() {
        article.setCaption("New caption.");
        assertEquals("New caption.", article.getCaption());
    }

    @Test
    public void testSetFileObject() {
    }

    @Test
    public void testSetContent() {
        article.setContent("New content.");
        assertEquals("New content.", article.getContent());
    }

    @Test
    public void testSetCategory() {
        article.setCategory("events");
        assertEquals("events", article.getCategory());
    }

    @Test
    public void testSetPubDate() {
        LocalDateTime time = LocalDateTime.of(2000, 11, 11, 20, 0, 0);
        article.setPubDate(time);
        assertEquals(time, article.getPubDate());
    }
    
}
