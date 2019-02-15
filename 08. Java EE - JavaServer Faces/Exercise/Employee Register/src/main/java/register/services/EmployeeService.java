package register.services;

import register.domain.entities.Employee;
import register.domain.models.binding.employee.EmployeeRegisterBindingModel;

public interface EmployeeService extends Service<Employee, String> {

    boolean saveEmployee(EmployeeRegisterBindingModel model);

    boolean deleteEmployee(String id);
}
