package news.domain;

import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;
// muita sopivia annotaatioita

@Entity
@Data
public class FileObject extends AbstractPersistable<Long> {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    private String contentType;
    private Long contentLength;

}
