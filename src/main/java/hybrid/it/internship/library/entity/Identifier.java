package hybrid.it.internship.library.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
