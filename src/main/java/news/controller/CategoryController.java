package news.controller;

import java.time.LocalDate;
import news.domain.Article;
import news.domain.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import news.repository.ArticleRepository;
import news.repository.CategoryRepository;
import news.repository.WriterRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/categories")
    public String list(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }
    
    @PostMapping("/categories")
    public String addCategory(@RequestParam String name) {
        categoryRepository.save(new Category(name));
        return "redirect:/categories";
    }
    
}
