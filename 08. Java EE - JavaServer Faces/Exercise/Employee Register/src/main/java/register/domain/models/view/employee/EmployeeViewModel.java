package register.domain.models.view.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import register.domain.entities.Employee;
import register.domain.models.view.Viewable;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeViewModel implements Viewable<Employee> {

    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    private Integer age;
}
