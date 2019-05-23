package hybrid.it.internship.library.web.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String author;
    private String title;
    private Integer totalCopies;
    private Integer availableCopies;

}
