package news.controller;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import news.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import news.repository.ArticleRepository;
import news.repository.CategoryRepository;
import news.repository.WriterRepository;
import news.service.CreateArticle;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewsController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostConstruct
    private String addBaseNews() {
        articleRepository.save(CreateArticle.abduction());
        articleRepository.save(CreateArticle.driverlessBuses());
        return "index";
    }

    @PostMapping("/")
    public String addArticle(@ModelAttribute Article article) {
        article.setPubDate(LocalDateTime.now());
        articleRepository.save(article);
        return "redirect:/";
    }

    @RequestMapping("/")
    public String listNews(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "pubDate");
        model.addAttribute("news", articleRepository.findAll(pageable));
        return "index";
    }
    
    @RequestMapping("/{id}")
    public String retrieveArticle(Model model, @PathVariable Long id) {
        model.addAttribute("article", articleRepository.getOne(id));
        return "articleItem";
    }

}
