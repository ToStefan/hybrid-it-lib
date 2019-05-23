package hybrid.it.internship.library.service.impl;

import hybrid.it.internship.library.entity.Book;
import hybrid.it.internship.library.repository.BookRepository;
import hybrid.it.internship.library.service.BookService;
import hybrid.it.internship.library.web.dto.BookDTO;
import hybrid.it.internship.library.web.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAll() {
        return bookMapper.toDTO(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO getById(Long id) {
        return bookMapper.toDTO(bookRepository.getOne(id));
    }

    @Override
    @Transactional
    public BookDTO create(BookDTO bookDTO) {
        return bookMapper.toDTO(bookRepository.save(bookMapper.toEntity(bookDTO)));
    }

    @Override
    @Transactional
    public BookDTO update(Long id, BookDTO bookDTO) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(IllegalStateException::new);

        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setTotalCopies(bookDTO.getTotalCopies());
        book.setAvailableCopies(bookDTO.getAvailableCopies());

        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
