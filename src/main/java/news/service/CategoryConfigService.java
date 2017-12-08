package news.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Data;
import news.domain.Category;
import org.springframework.stereotype.Service;

@Service
@Data
public class CategoryConfigService {

    private List<Category> categories;

    public CategoryConfigService() {
        categories = new ArrayList<Category>();
        categories.add(new Category("Current Events"));
        categories.add(new Category("Technology"));
        categories.add(new Category("Politics"));
        categories.add(new Category("Sports"));
        categories.add(new Category("Weather"));
    }

    public List<Category> getAllCategories() {
        return this.categories;
    }
    
}
