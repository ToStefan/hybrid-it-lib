package hybrid.it.internship.library.web.controller;

import hybrid.it.internship.library.service.RentService;
import hybrid.it.internship.library.service.impl.UserServiceImpl;
import hybrid.it.internship.library.web.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserServiceImpl userService;
    private final RentService rentService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<UserDTO> users = userService.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {

        UserDTO userDto = userService.getById(id);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {

        //UserDTO userDto = userService.getByUsername(username);

        //return new ResponseEntity<>(userDto, HttpStatus.OK);
        return null;
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {

        UserDTO retVal = userService.create(userDTO);

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {

        if(userService.existsByUsername(userDTO.getUsername()))
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        UserDTO retVal = userService.update(id, userDTO);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {

        if (rentService.existsByUserId(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
