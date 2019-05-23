package hybrid.it.internship.library.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book extends Identifier {

    @NotNull
    private String author;

    @NotNull
    private String title;

    @NotNull
    private Integer totalCopies;

    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;
}
