package hybrid.it.internship.library.service.impl;

import hybrid.it.internship.library.entity.Book;
import hybrid.it.internship.library.entity.MostRentedView;
import hybrid.it.internship.library.exception.EntityNotFoundException;
import hybrid.it.internship.library.repository.BookRepository;
import hybrid.it.internship.library.repository.MostRentedRepository;
import hybrid.it.internship.library.repository.RentRepository;
import hybrid.it.internship.library.service.RentService;
import hybrid.it.internship.library.web.dto.PageDTO;
import hybrid.it.internship.library.web.dto.RentDTO;
import hybrid.it.internship.library.web.mapper.RentMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final RentMapper rentMapper;
    private final BookRepository bookRepository;
    private final MostRentedRepository mostRentedRep;

    public RentServiceImpl(RentRepository rentRepository, RentMapper rentMapper, BookRepository bookRepository,
                           MostRentedRepository mostRentedRep) {
        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
        this.bookRepository = bookRepository;
        this.mostRentedRep = mostRentedRep;

    }

    @Value("${app.rentPeriodInDays:14}")
    private Integer rentPeriodInDays;

    @Override
    public boolean existsByBookId(Long bookId) {
        return rentRepository.existsByBookId(bookId);
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return rentRepository.existsByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentDTO> getOverdueRents(PageDTO pageDTO) {

        List<RentDTO> rentsDTOs = rentRepository.findAll(pageDTO.toPageRequest())
                .get()
                .map(rentMapper::toDTO)
                .collect(Collectors.toList());

        rentsDTOs.removeIf(rent -> !rent.getRentDate().plusDays(rentPeriodInDays).isBefore(LocalDateTime.now()));

        return rentsDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MostRentedView> getMostRented() {

        List<MostRentedView> mostRentedBooks = mostRentedRep.findAll();

        return mostRentedBooks;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentDTO> getAll(PageDTO pageDTO) {
        return rentRepository.findAll(pageDTO.toPageRequest())
                .get()
                .map(rentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RentDTO getById(Long id) {
        return rentMapper.toDTO(rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString())));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentDTO> getByBookId(Long bookId) {
        return rentMapper.toDTO(rentRepository.findByBookId(bookId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentDTO> getByUserId(Long userId) {
        return rentMapper.toDTO(rentRepository.findByUserId(userId));
    }

    @Override
    @Transactional
    public RentDTO create(RentDTO rentDTO) {

        RentDTO rentDto = rentMapper.toDTO(rentRepository.save(rentMapper.toEntity(rentDTO)));

        Optional<Book> book = bookRepository.findById(rentDTO.getBookId());
        if(book.get().getTotalCopies() > 0 ){
            book.get().setTotalCopies(book.get().getTotalCopies() - 1);
            bookRepository.save(book.get());
        }

        return rentDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rentRepository.deleteById(id);
    }
}
