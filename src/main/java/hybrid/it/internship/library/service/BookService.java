package hybrid.it.internship.library.service;

import hybrid.it.internship.library.web.dto.BookDTO;
import hybrid.it.internship.library.web.dto.PageDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAll(PageDTO pageDTO);
    BookDTO getById(Long id);
    BookDTO create(BookDTO bookDTO);
    BookDTO update(Long id, BookDTO bookDTO);
    void delete(Long id);
}
