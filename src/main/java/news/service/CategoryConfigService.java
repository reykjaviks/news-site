package news.service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Category> getAllCategories() {
        return this.categories;
    }

    public void initialize() {
        Category technology = new Category();
        technology.setName("technology");
        categories.add(technology);

        Category weather = new Category();
        weather.setName("weather");
        categories.add(weather);
        
        Category politics = new Category();
        politics.setName("politics");
        categories.add(politics);
        
        Category events = new Category();
        events.setName("events");
        categories.add(events);
    }
    
}