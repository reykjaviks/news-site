package news.controller;

import news.domain.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class NewsController {
    
    @Autowired
    private NewsRepository newsRepository;
    
    @RequestMapping("/add")
    public String foo() {
        return "add";
    }
    
    @PostMapping("/add")
    public String addNews(@RequestParam String title,
                          @RequestParam String caption,
                          @RequestParam String content) {
        News news = new News(title, caption, content);
        newsRepository.save(news);
        return "redirect:/add";
    }

}
