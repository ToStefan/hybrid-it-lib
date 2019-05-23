package hybrid.it.internship.library.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends Identifier{

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleName name;
}
