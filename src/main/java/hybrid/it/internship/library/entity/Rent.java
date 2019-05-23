package hybrid.it.internship.library.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rents")
public class Rent extends Identifier{

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "id")
    private Book book;
}
