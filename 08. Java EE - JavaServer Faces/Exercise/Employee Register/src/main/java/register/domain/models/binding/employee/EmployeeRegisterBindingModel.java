package register.domain.models.binding.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import register.domain.entities.Employee;
import register.domain.models.binding.Bindable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRegisterBindingModel implements Bindable<Employee> {

    @NotNull
    @Size(min = 1, max = 32)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 32)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 32)
    private String position;

    @NotNull
    @Min(0)
    private BigDecimal salary;

    @NotNull
    @Min(0)
    private Integer age;
}
