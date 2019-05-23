package hybrid.it.internship.library.service.impl;

import hybrid.it.internship.library.repository.RentRepository;
import hybrid.it.internship.library.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    @Override
    public boolean existsByBookId(Long bookId) {
        return rentRepository.existsByBookId(bookId);
    }
}
