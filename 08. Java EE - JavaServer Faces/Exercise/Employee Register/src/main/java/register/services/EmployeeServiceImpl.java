package register.services;

import org.modelmapper.ModelMapper;
import register.domain.entities.Employee;
import register.domain.models.binding.employee.EmployeeRegisterBindingModel;
import register.repositories.EmployeeRepository;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.logging.Logger;

public class EmployeeServiceImpl extends BaseService<Employee, String, EmployeeRepository> implements EmployeeService {

    private static final Logger LOG = Logger.getLogger(EmployeeServiceImpl.class.getName());


    @Inject
    public EmployeeServiceImpl(EmployeeRepository repository,
                               ModelMapper mapper,
                               Validator validator) {
        super(mapper, validator, repository);
    }

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public boolean saveEmployee(EmployeeRegisterBindingModel model) {
        if (model == null) {
            return false;
        }

        return create(model);
    }

    @Override
    public boolean deleteEmployee(String id) {
        return repository.delete(id);
    }
}
