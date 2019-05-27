package hybrid.it.internship.library.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RentDTO {

    private Long id;
    private LocalDateTime rentDate;
    private Long userId;
    private Long bookId;
}
