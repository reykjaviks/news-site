package news.controller;

import news.domain.Category;
import news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/categories/{name}")
    public String listArticlesByCategory(Model model, @PathVariable String name) {
        Category category = categoryRepository.findByName(name);
        model.addAttribute("category", category);
        model.addAttribute("news", category.getArticles());
        return "category";
    }

}
