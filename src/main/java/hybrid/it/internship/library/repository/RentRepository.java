package hybrid.it.internship.library.repository;

import hybrid.it.internship.library.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    boolean existsByBookId(Long id);
    boolean existsByUserId(Long userId);

    List<Rent> findByBookId(Long bookId);
    List<Rent> findByUserId(Long userId);
}
