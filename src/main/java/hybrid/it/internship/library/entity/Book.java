package hybrid.it.internship.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", unique = true, nullable = false)
    private long id;

    @Getter @Setter
    @Column(name = "author", unique = false, nullable = false)
    private String author;

    @Getter @Setter
    @Column(name = "title", unique = false, nullable = false)
    private String title;

    @Getter @Setter
    @Column(name = "total_copies", unique = false, nullable = false)
    private Integer totalCopies;

    @Getter @Setter
    @Column(name = "available_copies", unique = false, nullable = false)
    private Integer availableCopies;

    public Book() {

    }


}
