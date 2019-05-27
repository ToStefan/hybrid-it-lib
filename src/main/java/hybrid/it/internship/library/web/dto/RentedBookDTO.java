package hybrid.it.internship.library.web.dto;

import lombok.Data;

@Data
public class RentedBookDTO {

    private String author;
    private String title;
    private Integer rentedCount;

}
