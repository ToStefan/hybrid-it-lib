package hybrid.it.internship.library.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
