package hybrid.it.internship.library.service.impl;

import hybrid.it.internship.library.entity.Book;
import hybrid.it.internship.library.entity.User;
import hybrid.it.internship.library.exceptions.EntityNotFoundException;
import hybrid.it.internship.library.repository.UserRepository;
import hybrid.it.internship.library.service.UserService;
import hybrid.it.internship.library.web.dto.UserDTO;
import hybrid.it.internship.library.web.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return userMapper.toDTO(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getById(Long id) {
        return userMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getByUsername(String username) {
        return userMapper.toDTO(userRepository.findByUsername(username));
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return userMapper.toDTO(userRepository.save(user));

    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}