package sbojbg.domain.models.binding.jobapp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbojbg.domain.entities.JobApplication;
import sbojbg.domain.enums.Sector;
import sbojbg.domain.models.binding.Bindable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class JobApplicationAddBindingModel implements Bindable<JobApplication> {

    @NotNull
    private Sector sector;

    @NotNull
    @Size(min = 1, max = 32)
    private String profession;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal salary;

    private String description;
}
