package fdmcjsf.domain.models.binding;

import fdmcjsf.domain.entities.Cat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CatBindingModel implements Bindable<Cat> {

    @NotNull
    @Size(min = 2, max = 10)
    private String name;

    @NotNull
    @Size(min = 5, max = 20)
    private String breed;

    @NotNull
    @Size(min = 3, max = 15)
    private String color;

    @NotNull
    @Min(value = 1)
    @Max(value = 31)
    @Column(nullable = false)
    private Integer age;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull
    @Size(min = 4, max = 6)
    private String gender;

    @NotNull
    @PastOrPresent
    private LocalDate addedOn;

    private boolean hasPassport;
}
