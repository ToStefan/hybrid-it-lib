package hybrid.it.internship.library.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends Identifier{

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleName name;
}
