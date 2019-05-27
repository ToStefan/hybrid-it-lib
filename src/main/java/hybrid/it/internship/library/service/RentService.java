package hybrid.it.internship.library.service;

import hybrid.it.internship.library.web.dto.RentDTO;
import hybrid.it.internship.library.web.dto.RentedBookDTO;

import java.util.List;

public interface RentService {
    boolean existsByBookId(Long bookId);
    boolean existsByUserId(Long userId);

    List<RentDTO> getOverdueRents();
    List<RentedBookDTO> getMostRented();

    List<RentDTO> getAll();
    RentDTO getById(Long id);
    List<RentDTO> getByBookId(Long bookId);
    List<RentDTO> getByUserId(Long userId);
    RentDTO create(RentDTO rentDTO);
    void delete(Long id);
}
