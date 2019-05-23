package hybrid.it.internship.library.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDTO {

    private Long id;
    private String author;
    private String title;
    private Integer totalCopies;
    private Integer availableCopies;
}