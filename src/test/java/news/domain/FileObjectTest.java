package news.domain;

import news.service.ArticleConfigService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileObjectTest {
    
    ArticleConfigService articleConfigService;
    FileObject fo;
    
    public FileObjectTest() {
    }

    @Before
    public void setUp() {
        articleConfigService = new ArticleConfigService();
        fo = articleConfigService.findImage("src/main/resources/images/driverless_bus.jpg");
    }

    @Test
    public void testGetContent() {
        byte[] expResult = articleConfigService.findImage("src/main"
                + "/resources/images/driverless_bus.jpg").getContent();
        assertArrayEquals(expResult, fo.getContent());
    }

    @Test
    public void testGetContentType() {
        assertEquals("image/jpeg", fo.getContentType());
    }

    @Test
    public void testSetContent() {
        byte[] content = articleConfigService.findImage("src/main"
                + "/resources/images/c-project2.jpg").getContent();
        fo.setContent(content);
        assertEquals(content, fo.getContent());
    }

    @Test
    public void testSetContentType() {
        fo.setContentType("image/gif");
        assertEquals("image/gif", fo.getContentType());
    }

    // TODO: Supply test code
    @Test
    public void testSetContentLength() {
    }

}
