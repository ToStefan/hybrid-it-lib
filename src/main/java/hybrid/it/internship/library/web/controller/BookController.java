package hybrid.it.internship.library.web.controller;

import hybrid.it.internship.library.service.impl.BookServiceImpl;
import hybrid.it.internship.library.service.impl.RentServiceImpl;
import hybrid.it.internship.library.web.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/book")
public class BookController {

    private final BookServiceImpl bookService;
    private final RentServiceImpl rentService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {

        List<BookDTO> bookDTOS = bookService.getAll();

        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {

        BookDTO bookDTO = bookService.getById(id);

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<BookDTO> newBook(@RequestBody BookDTO bookDTO) {

        bookDTO.setAvailableCopies(bookDTO.getTotalCopies());

        BookDTO retVal = bookService.create(bookDTO);

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {

        BookDTO retVal = bookService.update(id, bookDTO);

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {

        if (rentService.existsByBookId(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            bookService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
