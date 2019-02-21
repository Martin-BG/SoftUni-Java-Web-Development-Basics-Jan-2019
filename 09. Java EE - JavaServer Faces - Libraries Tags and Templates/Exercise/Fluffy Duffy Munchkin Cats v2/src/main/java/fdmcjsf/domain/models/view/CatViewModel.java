package fdmcjsf.domain.models.view;

import fdmcjsf.domain.entities.Cat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CatViewModel implements Viewable<Cat> {

    private String name;
    private String breed;
    private String color;
    private BigDecimal price;
    private String gender;
    private LocalDate addedOn;
}
