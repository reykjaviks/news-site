package news.controller;

import java.time.LocalDate;
import news.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import news.repository.ArticleRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewsController {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @PostMapping("/")
    public String addArticle(@RequestParam String title, @RequestParam String caption) {
        articleRepository.save(new Article(title, caption, LocalDate.now()));
        return "redirect:/";
    }
    
    @RequestMapping("/")
    public String listNews(Model model) {
        model.addAttribute("news", articleRepository.findAll());
       return "index"; 
    }
    
    @RequestMapping("/{id}")
    public String retrieveArticle(Model model, @PathVariable Long id) {
        model.addAttribute("article", articleRepository.getOne(id));
        return "articleItem";
    }

}
