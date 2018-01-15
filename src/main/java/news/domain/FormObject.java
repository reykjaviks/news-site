package news.domain;

import lombok.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormObject {
    
    @NotEmpty
    @Max(200)
    private String title;

    @NotEmpty
    @Max(1000)
    private String caption;

    @NotEmpty
    @Max(100000)
    private String content;

    @NotNull
    private String category;
    
}
