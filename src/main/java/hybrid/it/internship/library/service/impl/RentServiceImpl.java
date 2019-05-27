package hybrid.it.internship.library.service.impl;

import hybrid.it.internship.library.entity.Book;
import hybrid.it.internship.library.exceptions.EntityNotFoundException;
import hybrid.it.internship.library.repository.BookRepository;
import hybrid.it.internship.library.repository.RentRepository;
import hybrid.it.internship.library.service.RentService;
import hybrid.it.internship.library.web.dto.RentDTO;
import hybrid.it.internship.library.web.dto.RentedBookDTO;
import hybrid.it.internship.library.web.mapper.RentMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toMap;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final RentMapper rentMapper;
    private final BookRepository bookRepository;

    @Value("${app.rentPeriodInDays:14}")
    private Integer rentPeriodInDays;

    public RentServiceImpl(RentRepository rentRepository, RentMapper rentMapper, BookRepository bookRepository) {
        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
        this.bookRepository = bookRepository;
    }

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
    public List<RentDTO> getOverdueRents() {

        List<RentDTO> rentsDTOs = rentMapper.toDTO(rentRepository.findAll());

        rentsDTOs.removeIf(rent -> !rent.getRentDate().plusDays(rentPeriodInDays).isBefore(LocalDateTime.now()));

        return rentsDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentedBookDTO> getMostRented() {

        List<RentedBookDTO> result = new ArrayList<>();
        List<RentDTO> rentsDTOs = rentMapper.toDTO(rentRepository.findAll());
        Map<Long, Integer> bookCounter = new HashMap<>();

        for (RentDTO r : rentsDTOs) {
            int count = bookCounter.containsKey(r.getBookId()) ? bookCounter.get(r.getBookId()) : 0;
            bookCounter.put(r.getBookId(), count + 1);
        }

        Map<Long, Integer> sorted = bookCounter
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        Iterator it = sorted.entrySet().iterator();
        int count = sorted.size() > 5 ? 5 : sorted.size();
        int counter = 1;

        while (counter <= count) {
            Map.Entry pair = (Map.Entry) it.next();
            Book book = bookRepository.getOne(Long.parseLong(String.valueOf(pair.getKey())));
            RentedBookDTO rbDTO = new RentedBookDTO();
            rbDTO.setAuthor(book.getAuthor());
            rbDTO.setTitle(book.getTitle());
            rbDTO.setRentedCount(Integer.parseInt(String.valueOf(pair.getValue())));
            result.add(rbDTO);
            counter++;
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentDTO> getAll() {
        return rentMapper.toDTO(rentRepository.findAll());
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
        return rentMapper.toDTO(rentRepository.save(rentMapper.toEntity(rentDTO)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rentRepository.deleteById(id);
    }
}
