package hybrid.it.internship.library.service;

import hybrid.it.internship.library.entity.Book;
import hybrid.it.internship.library.repository.BookRepository;
import hybrid.it.internship.library.service.impl.BookServiceImpl;
import hybrid.it.internship.library.web.dto.PageDTO;
import hybrid.it.internship.library.web.mapper.BookMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    private static PageDTO pageDto = new PageDTO(1, 1);

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testGetAll_EmptyList() {

        Page<Book> pageEmpty = Page.empty();

        doReturn(pageEmpty).when(bookRepository).findAll(any(Pageable.class));
        assertTrue(bookService.getAll(pageDto).isEmpty());
    }

    @Test
    public void testGetAll_OneInList() {

        List<Book> books = new ArrayList<>();
        Book book = Book.builder().author("author").title("title").totalCopies(10).availableCopies(10).build();
        books.add(book);
        Page<Book> booksPage = new PageImpl<>(books);

        doReturn(booksPage).when(bookRepository).findAll(any(Pageable.class));
        assertTrue(bookService.getAll(pageDto).size() == 1);
    }

}

