package register.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String firstName;


    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String position;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private BigDecimal salary;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer age;
}
