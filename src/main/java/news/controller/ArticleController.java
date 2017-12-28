package news.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import news.domain.Article;
import news.domain.Category;
import news.domain.FileObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import news.repository.ArticleRepository;
import news.repository.CategoryRepository;
import news.repository.FileObjectRepository;
import news.service.ArticleConfigService;
import news.service.CategoryConfigService;
import news.service.FileObjectConfigService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FileObjectRepository fileObjectRepository;
    @Autowired
    private ArticleConfigService articleConfigService;
    @Autowired
    private CategoryConfigService categoryConfigService;
    @Autowired
    private FileObjectConfigService fileObjectConfigService;

    @PostConstruct
    public String init() {
        categoryRepository.saveAll(categoryConfigService.getAllCategories());
        articleRepository.saveAll(articleConfigService.getAllArticles());
        return "index";
    }
    
    @PostMapping("/")
    public String addArticle(@ModelAttribute Article article,
            @RequestParam("file") MultipartFile file) throws IOException {
        // Todo: Generate an error message if category is empty
        if (article.getCategory() != null) {
            FileObject fo = fileObjectConfigService.createFileObject(file);
            fileObjectRepository.save(fo);
            categoryRepository.findByName(article.getCategory()).getArticles().add(article);
            articleRepository.save(articleConfigService.createArticle(article, fo));
        }
        return "redirect:/";
    }
    
    @RequestMapping("/")
    public String listArticles(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "pubDate");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("news", articleRepository.findAll(pageable));
        return "index";
    }

    @RequestMapping("/{id}")
    public String retrieveArticle(Model model, @PathVariable Long id) {
        Category category = categoryRepository.findByName(articleRepository.getOne(id).getCategory());
        model.addAttribute("category", category);
        model.addAttribute("article", articleRepository.getOne(id));
        return "articleItem";
    }

}
