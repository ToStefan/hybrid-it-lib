package hybrid.it.internship.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Getter @Setter
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Getter @Setter
    @Column(name = "username", nullable = false)
    private String username;

    @Getter @Setter
    @Column(name = "password", nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roles = new HashSet<>();

    public User(){

    }

}
