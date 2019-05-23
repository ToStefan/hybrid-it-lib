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
        BookDTO bookDTO = BookDTO.builder()
                .id(entity.getId())
                .author(entity.getAuthor())
                .title(entity.getTitle())
                .totalCopies(entity.getTotalCopies())
                .availableCopies(entity.getAvailableCopies())
                .build();
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
        Book book = Book.builder()
                .author(bookDTO.getAuthor())
                .title(bookDTO.getTitle())
                .totalCopies(bookDTO.getTotalCopies())
                .availableCopies(bookDTO.getAvailableCopies())
                .build();
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
