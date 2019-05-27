package hybrid.it.internship.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Immutable
public class MostRentedView extends Identifier{

    private String author;
    private String title;
    private Integer rentedCount;
}
