package news.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Article extends AbstractPersistable<Long> {

    private String title;

    @Column(length = 10000)
    private String caption;

    @Column(length = 1000000)
    private String content;

    private String category;

    @OneToOne
    private FileObject fileObject;

    private LocalDateTime pubDate;

}
