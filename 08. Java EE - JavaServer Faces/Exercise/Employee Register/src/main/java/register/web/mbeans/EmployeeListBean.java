package register.web.mbeans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import register.domain.models.view.employee.EmployeeViewModel;
import register.services.EmployeeService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@Model
public class EmployeeListBean {

    private List<EmployeeViewModel> employees = new ArrayList<>();
    private BigDecimal totalMoneyNeeded = BigDecimal.ZERO;
    private BigDecimal averageSalary = BigDecimal.ZERO;

    @Inject
    public EmployeeListBean(EmployeeService service) {
        employees = service.findAll(EmployeeViewModel.class);
        init();
    }

    private void init() {
        if (!employees.isEmpty()) {
            totalMoneyNeeded = this.employees.stream()
                    .map(EmployeeViewModel::getSalary)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            averageSalary = totalMoneyNeeded
                    .divide(BigDecimal.valueOf(employees.size()), 2, RoundingMode.HALF_UP);
        }
    }

    public List<EmployeeViewModel> getEmployees() {
        return Collections.unmodifiableList(employees);
    }
}



