package news.controller;

import news.domain.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    
    @Autowired
    private NewsRepository newsRepository;
    
    @PostMapping("/")
    public String addNews(@RequestParam String title, @RequestParam String caption) {
        newsRepository.save(new News(title, caption));
        return "redirect:/";
    }
    
    @RequestMapping("/")
    public String listNews(Model model) {
        model.addAttribute("newsList", newsRepository.findAll());
       return "index"; 
    }

}
