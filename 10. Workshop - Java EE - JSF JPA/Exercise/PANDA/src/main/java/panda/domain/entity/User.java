package panda.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import panda.domain.entity.role.Role;
import panda.domain.entity.role.RoleConverter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_username", columnNames = {"username"}),
                @UniqueConstraint(name = "uk_users_email", columnNames = {"email"}),
        }
)
@NamedQuery(name = "User.findByUsername",
        query = "SELECT u FROM User u WHERE u.username = :username")
public class User extends BaseUuidEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String username;

    @NotNull
    @Size(min = 75, max = 75)
    @Column(nullable = false, length = 75)
    private String password;

    @NotNull
    @Email
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String email;

    @NotNull
    @Column(nullable = false, length = 5)
    @Convert(converter = RoleConverter.class)
    private Role role;
}
