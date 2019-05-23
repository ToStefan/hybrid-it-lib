package hybrid.it.internship.library.service;

import hybrid.it.internship.library.entity.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAll();
    Book getById(Long id);
    Book create(Book book);
    Book update(Book book);
    void delete(Long id);

}
