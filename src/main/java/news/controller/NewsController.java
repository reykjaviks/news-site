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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NewsController {

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

    @PostConstruct
    public String init() {
        categoryConfigService.initialize();
        categoryRepository.saveAll(categoryConfigService.getAllCategories());
        
        articleConfigService.initialize();
        articleRepository.saveAll(articleConfigService.getAllArticles());
        
        return "index";
    }

    @PostMapping("/")
    public String addArticle(@ModelAttribute Article article,
            @RequestParam("file") MultipartFile file) throws IOException {

        FileObject fo = new FileObject();
        fo.setContent(file.getBytes());
        fo.setContentLength(file.getSize());
        fo.setContentType(file.getContentType());
        fileObjectRepository.save(fo);

        article.setFileObject(fo);
        article.setPubDate(LocalDateTime.now());

        if (article.getCategory() != null) {
            Category category = categoryRepository.findByName(article.getCategory());
            category.getArticles().add(article);
            articleRepository.save(article);
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

    @RequestMapping("/categories/{name}")
    public String listArticlesByCategory(Model model, @PathVariable String name) {
        Category category = categoryRepository.findByName(name);
        model.addAttribute("category", category);
        model.addAttribute("news", category.getArticles());
        return "category";
    }

    @RequestMapping("/{id}")
    public String retrieveArticle(Model model, @PathVariable Long id) {
        Category category = categoryRepository.findByName(articleRepository.getOne(id).getCategory());
        model.addAttribute("category", category);
        model.addAttribute("article", articleRepository.getOne(id));
        return "articleItem";
    }

    @RequestMapping("/images/{id}")
    public ResponseEntity<byte[]> retrieveImage(Model model, @PathVariable Long id) {
        FileObject fo = fileObjectRepository.getOne(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }

}
