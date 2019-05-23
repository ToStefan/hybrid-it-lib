package hybrid.it.internship.library.web.controller;

import hybrid.it.internship.library.service.impl.BookService;
import hybrid.it.internship.library.service.impl.RentService;
import hybrid.it.internship.library.web.dto.BookDTO;
import hybrid.it.internship.library.web.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RentService rentService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {

        List<BookDTO> bookDTOS = bookMapper.toDTO(bookService.getAll());

        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {

        BookDTO bookDTO = bookMapper.toDTO(bookService.getById(id));

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<BookDTO> newBook(@RequestBody BookDTO bookDTO) {

        bookDTO.setAvailableCopies(bookDTO.getTotalCopies());

        BookDTO retVal = bookMapper.toDTO(bookService.create(bookMapper.toEntity(bookDTO)));

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO){

        BookDTO retVal = bookMapper.toDTO(bookService.update(bookMapper.toEntity(bookDTO)));

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {

        if(rentService.existsByBookId(id)){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            bookService.delete(id);
        }
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
