package news.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Article extends AbstractPersistable<Long> {

    private String title;

    @Column(length = 10000)
    private String caption;

    @OneToOne(cascade = {CascadeType.ALL})
    private FileObject fileObject;

    @Column(length = 1000000)
    private String content;

    private String category;

    private LocalDateTime pubDate;
    
}
