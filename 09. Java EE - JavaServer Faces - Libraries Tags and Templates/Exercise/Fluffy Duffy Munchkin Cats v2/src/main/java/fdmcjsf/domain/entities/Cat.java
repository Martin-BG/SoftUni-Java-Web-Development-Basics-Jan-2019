package fdmcjsf.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 10)
    @Column(nullable = false, length = 10)
    private String name;

    @NotNull
    @Size(min = 5, max = 20)
    @Column(nullable = false, length = 20)
    private String breed;

    @NotNull
    @Size(min = 3, max = 15)
    @Column(nullable = false, length = 15)
    private String color;

    @Min(value = 1)
    @Max(value = 31)
    @Column(nullable = false)
    private int age;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal price;

    @NotNull
    @Size(min = 4, max = 6)
    @Column(nullable = false, length = 6)
    private String gender;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate addedOn;

    @Column(nullable = false)
    private boolean hasPassport;
}
