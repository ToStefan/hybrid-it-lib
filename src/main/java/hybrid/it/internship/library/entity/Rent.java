package hybrid.it.internship.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rents")
public class Rent {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id", unique = true, nullable = false)
    private long id;

    @Getter @Setter
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "rented", unique = false, nullable = false)
    private Date rented;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Rent(){

    }


}
