package hybrid.it.internship.library.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Identifier {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roles = new HashSet<>();
}
