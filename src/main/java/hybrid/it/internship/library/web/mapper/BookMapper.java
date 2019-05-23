package hybrid.it.internship.library.web.mapper;

import hybrid.it.internship.library.entity.Book;
import hybrid.it.internship.library.web.dto.BookDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper implements Mapper<Book, BookDTO> {

    @Override
    public BookDTO toDTO(Book entity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(entity.getId());
        bookDTO.setAuthor(entity.getAuthor());
        bookDTO.setTitle(entity.getTitle());
        bookDTO.setTotalCopies(entity.getTotalCopies());
        bookDTO.setAvailableCopies(entity.getAvailableCopies());
        return bookDTO;
    }

    @Override
    public List<BookDTO> toDTO(Collection<Book> entities) {
        return entities
                .stream()
                .map(book -> toDTO(book))
                .collect(Collectors.toList());
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        if(bookDTO.getId() != null){
            book.setId(bookDTO.getId());
        }
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setTotalCopies(bookDTO.getTotalCopies());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        return book;
    }

    @Override
    public List<Book> toEntity(Collection<BookDTO> dtos) {
        return dtos
                .stream()
                .map(bookDTO -> toEntity(bookDTO))
                .collect(Collectors.toList());

    }
}
