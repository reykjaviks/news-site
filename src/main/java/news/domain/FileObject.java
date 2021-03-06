package news.domain;

import javax.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
public class FileObject extends AbstractPersistable<Long> {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    private String contentType;
    private Long contentLength;

}
