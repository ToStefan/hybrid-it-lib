package hybrid.it.internship.library.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
@AllArgsConstructor
public class PageDTO {

    private int page = 0;
    private int elementsCount = 100;

    public PageRequest toPageRequest(){
        return  PageRequest.of(this.page, this.elementsCount);
    }
}
