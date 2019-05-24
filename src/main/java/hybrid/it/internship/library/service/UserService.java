package hybrid.it.internship.library.service;


import hybrid.it.internship.library.web.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();
    UserDTO getById(Long id);
    UserDTO getByUsername(String username);
    UserDTO create(UserDTO userDTO);
    UserDTO update(Long id, UserDTO userDTO);
    void delete(Long id);
    boolean existsByUsername(String username);
}
