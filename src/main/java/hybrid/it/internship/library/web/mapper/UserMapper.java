package hybrid.it.internship.library.web.mapper;

import hybrid.it.internship.library.entity.User;
import hybrid.it.internship.library.web.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO userDto = UserDTO.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
        return userDto;
    }

    @Override
    public List<UserDTO> toDTO(Collection<User> entities) {
        return entities
                .stream()
                .map(user -> toDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
        return user;
    }

    @Override
    public List<User> toEntity(Collection<UserDTO> userDTOS) {
        return userDTOS
                .stream()
                .map(userDTO -> toEntity(userDTO))
                .collect(Collectors.toList());
    }
}
