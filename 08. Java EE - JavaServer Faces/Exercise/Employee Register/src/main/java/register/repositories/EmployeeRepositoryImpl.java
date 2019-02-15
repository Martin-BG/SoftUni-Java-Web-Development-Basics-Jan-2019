package register.repositories;


import register.domain.entities.Employee;

import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
public class EmployeeRepositoryImpl extends BaseCrudRepository<Employee, String> implements EmployeeRepository {

    private static final Logger LOG = Logger.getLogger(EmployeeRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }
}
