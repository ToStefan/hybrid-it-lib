package hybrid.it.internship.library.service.impl;

import hybrid.it.internship.library.repository.RentRepository;
import hybrid.it.internship.library.service.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService implements IRentService {

    @Autowired
    private RentRepository rentRepository;

    @Override
    public boolean existsByBookId(Long bookId) {
        return rentRepository.existsByBookId(bookId);
    }
}
