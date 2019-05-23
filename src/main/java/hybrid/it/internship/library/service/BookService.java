package hybrid.it.internship.library.service;

import hybrid.it.internship.library.web.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAll();
    BookDTO getById(Long id);
    BookDTO create(BookDTO bookDTO);
    BookDTO update(Long id, BookDTO bookDTO);
    void delete(Long id);
}
