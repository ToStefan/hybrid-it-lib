package hybrid.it.internship.library.repository;

import hybrid.it.internship.library.entity.MostRentedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MostRentedRepository extends JpaRepository<MostRentedView, Long> {

}
