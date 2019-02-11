package metube.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
public class User extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 32)
    @Column(unique = true, nullable = false, length = 32)
    private String username;

    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String password;

    @NotNull
    @Size(min = 1, max = 64)
    @Column(unique = true, nullable = false, length = 64)
    private String email;

    @OneToMany(targetEntity = Tube.class, fetch = FetchType.LAZY, mappedBy = "uploader")
    private List<Tube> tubses = new ArrayList<>();
}
