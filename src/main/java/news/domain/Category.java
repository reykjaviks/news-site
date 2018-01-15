package news.domain;

import java.util.*;
import javax.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Category extends AbstractPersistable<Long> {
    
    private String name;
    
    @OneToMany
    private List<Article> articles;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        if (this.articles == null) {
            this.articles = new ArrayList<>();
        }
        return this.articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
