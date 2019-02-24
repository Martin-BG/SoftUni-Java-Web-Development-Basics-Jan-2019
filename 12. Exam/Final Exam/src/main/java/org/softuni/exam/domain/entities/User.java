package org.softuni.exam.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findByUsername",
        query = "SELECT u FROM User u WHERE u.username = :username")
public class User extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 32)
    @Column(unique = true, nullable = false, length = 32)
    private String username;

    @NotNull
    @Size(min = 75, max = 75)
    @Column(nullable = false, length = 75)
    private String password;

    @NotNull
    @Email
    @Size(min = 1, max = 64)
    @Column(unique = true, nullable = false, length = 64)
    private String email;
}
