package hybrid.it.internship.library.web.mapper;

import hybrid.it.internship.library.entity.Rent;
import hybrid.it.internship.library.service.BookService;
import hybrid.it.internship.library.service.UserService;
import hybrid.it.internship.library.web.dto.RentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RentMapper implements Mapper<Rent, RentDTO> {

    private final UserService userService;
    private final BookService bookService;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Override
    public RentDTO toDTO(Rent entity) {
        RentDTO rentDTO = RentDTO.builder()
                .id(entity.getId())
                .rentDate(entity.getRentDate())
                .userId(entity.getUser().getId())
                .bookId(entity.getBook().getId())
                .build();
        return rentDTO;
    }

    @Override
    public List<RentDTO> toDTO(Collection<Rent> entities) {
        return entities
                .stream()
                .map(rent -> toDTO(rent))
                .collect(Collectors.toList());
    }

    @Override
    public Rent toEntity(RentDTO rentDTO) {
        Rent rent = Rent.builder()
                .rentDate(rentDTO.getRentDate())
                .user(userMapper.toEntity(userService.getById(rentDTO.getUserId())))
                .book(bookMapper.toEntity(bookService.getById(rentDTO.getBookId())))
                .build();
        return rent;
    }

    @Override
    public List<Rent> toEntity(Collection<RentDTO> rentDTOS) {
        return rentDTOS
                .stream()
                .map(rentDTO -> toEntity(rentDTO))
                .collect(Collectors.toList());
    }
}
