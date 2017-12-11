package news.controller;

import news.repository.ArticleRepository;
import news.repository.CategoryRepository;
import news.repository.FileObjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsControllerTest {
    
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FileObjectRepository fileObjectRepository;

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOkListArticles() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("news"));
    }

    @Test
    public void statusOkListArticlesByCategory() throws Exception {
        mockMvc.perform(get("/categories/weather"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attributeExists("news"));
    }

    @Test
    public void statusOkRetrieveArticle() throws Exception {
        mockMvc.perform(get("/5"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attributeExists("article"));
    }

    // TODO: Supply test code
    @Test
    public void postOk() throws Exception {
    }
    
    
}
