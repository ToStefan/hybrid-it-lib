package hybrid.it.internship.library.entity;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book extends Identifier{

    @NotNull
    private String author;

    @NotNull
    private String title;

    @NotNull
    private Integer totalCopies;

    @NotNull
    @Column(name = "available_copies")
    private Integer availableCopies;
}
