package hybrid.it.internship.library.web.controller;

import hybrid.it.internship.library.service.impl.RentServiceImpl;
import hybrid.it.internship.library.web.dto.RentDTO;
import hybrid.it.internship.library.web.dto.RentedBookDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/rent")
public class RentController {

    private final RentServiceImpl rentService;

    @GetMapping
    public ResponseEntity<List<RentDTO>> getRents() {

        List<RentDTO> rents = rentService.getAll();
        return new ResponseEntity<>(rents, HttpStatus.OK);
    }

    @GetMapping(value = "/overdue")
    public ResponseEntity<List<RentDTO>> getOverdueRents() {

        List<RentDTO> rentDTO = rentService.getOverdueRents();
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);

    }

    @GetMapping(value = "/most-rented")
    public ResponseEntity<List<RentedBookDTO>> getMostRented() {

        List<RentedBookDTO> rbdtos = rentService.getMostRented();
        return new ResponseEntity<>(rbdtos, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RentDTO> getRentById(@PathVariable("id") Long id) {

        RentDTO rentDTO = rentService.getById(id);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity<List<RentDTO>> getRentByBookId(@PathVariable("id") Long id) {

        List<RentDTO> rentDTO = rentService.getByBookId(id);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<RentDTO>> getRentByUserId(@PathVariable("id") Long id) {

        List<RentDTO> rentDTO = rentService.getByUserId(id);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentDTO> create(@RequestBody RentDTO rentDTO) {

        RentDTO retVal = rentService.create(rentDTO);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteRent(@PathVariable("id") Long id) {

        rentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
