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
    }

    public void initialize() {
        Category technology = new Category();
        technology.setName("technology");
        categories.add(technology);
        Category weather = new Category();
        weather.setName("weather");
        Category politics = new Category();
        politics.setName("politics");
        Category events = new Category();
        events.setName("events");
        
        categories.add(technology);
        categories.add(weather);
        categories.add(politics);
        categories.add(events);
    }

    public List<Category> getAllCategories() {
        return this.categories;
    }
    
}
