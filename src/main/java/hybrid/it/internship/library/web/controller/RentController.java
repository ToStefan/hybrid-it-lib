package hybrid.it.internship.library.web.controller;

import hybrid.it.internship.library.entity.MostRentedView;
import hybrid.it.internship.library.service.impl.RentServiceImpl;
import hybrid.it.internship.library.web.dto.PageDTO;
import hybrid.it.internship.library.web.dto.RentDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/rent")
public class RentController {

    private final RentServiceImpl rentService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/search")
    public ResponseEntity<List<RentDTO>> search(@RequestBody PageDTO pageDTO) {

        List<RentDTO> rents = rentService.getAll(pageDTO);
        return new ResponseEntity<>(rents, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(value = "/overdue")
    public ResponseEntity<List<RentDTO>> getOverdueRents(@RequestBody PageDTO pageDTO) {

        List<RentDTO> rentDTO = rentService.getOverdueRents(pageDTO);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping(value = "/most-rented")
    public ResponseEntity<List<MostRentedView>> getMostRented() {

        List<MostRentedView> mostRentedBooks = rentService.getMostRented();
        return new ResponseEntity<>(mostRentedBooks, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RentDTO> getRentById(@PathVariable("id") Long id) {

        RentDTO rentDTO = rentService.getById(id);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/book/{id}")
    public ResponseEntity<List<RentDTO>> getRentByBookId(@PathVariable("id") Long id) {

        List<RentDTO> rentDTO = rentService.getByBookId(id);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<RentDTO>> getRentByUserId(@PathVariable("id") Long id) {

        List<RentDTO> rentDTO = rentService.getByUserId(id);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<RentDTO> create(@RequestBody RentDTO rentDTO) {

        RentDTO retVal = rentService.create(rentDTO);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteRent(@PathVariable("id") Long id) {

        rentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
