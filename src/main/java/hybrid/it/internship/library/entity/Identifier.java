package hybrid.it.internship.library.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
}
