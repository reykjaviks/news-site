package news.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormObject {
    
    @NotEmpty
    @Max(200)
    private String title;

    @Max(1000)
    private String caption;

    private FileObject fileObject;

    @Max(100000)
    private String content;

    @NotEmpty
    private String category;

    private LocalDateTime pubDate;
    
}
