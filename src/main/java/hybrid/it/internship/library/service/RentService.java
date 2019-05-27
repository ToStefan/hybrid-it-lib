package hybrid.it.internship.library.service;

import hybrid.it.internship.library.entity.MostRentedView;
import hybrid.it.internship.library.web.dto.PageDTO;
import hybrid.it.internship.library.web.dto.RentDTO;

import java.util.List;

public interface RentService {
    boolean existsByBookId(Long bookId);
    boolean existsByUserId(Long userId);

    List<RentDTO> getOverdueRents(PageDTO pageDTO);
    List<MostRentedView> getMostRented();

    List<RentDTO> getAll(PageDTO pageDTO);
    RentDTO getById(Long id);
    List<RentDTO> getByBookId(Long bookId);
    List<RentDTO> getByUserId(Long userId);
    RentDTO create(RentDTO rentDTO);
    void delete(Long id);
}
