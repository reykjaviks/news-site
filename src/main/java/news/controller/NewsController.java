package news.controller;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import news.domain.Article;
import news.domain.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import news.repository.ArticleRepository;
import news.repository.CategoryRepository;
import news.service.ArticleConfigService;
import news.service.CategoryConfigService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewsController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ArticleConfigService articleConfigService;
    @Autowired
    private CategoryConfigService categoryConfigService;

    @PostConstruct
    public String init() {
        categoryConfigService.initialize();
        categoryRepository.saveAll(categoryConfigService.getAllCategories());

        articleConfigService.initialize();
        articleRepository.saveAll(articleConfigService.getAllArticles());

        return "index";
    }

    @PostMapping("/")
    public String addArticle(@ModelAttribute Article article) {
        Category category = categoryRepository.findByName(article.getCategory());
        category.getArticles().add(article); //Nyt mun kategoriaan on liitetty uutinen
        article.setPubDate(LocalDateTime.now());
        articleRepository.save(article);
        return "redirect:/";
    }

    @RequestMapping("/")
    public String listNews(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "pubDate");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("news", articleRepository.findAll(pageable));
        return "index";
    }

    @RequestMapping("/{id}")
    public String retrieveArticle(Model model, @PathVariable Long id) {
        model.addAttribute("category", articleRepository.getOne(id).getCategory());
        model.addAttribute("article", articleRepository.getOne(id));
        return "articleItem";
    }

    @RequestMapping("/categories/{name}")
    public String listArticlesByCategory(Model model, @PathVariable String name) {
        Category category = categoryRepository.findByName(name);
        model.addAttribute("category", category);
        model.addAttribute("news", category.getArticles());
        return "category";
    }

}
