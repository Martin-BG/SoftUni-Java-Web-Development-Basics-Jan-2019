package sbojbg.repositories;


import sbojbg.domain.entities.JobApplication;

import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
public class JobApplicationRepositoryImpl extends BaseCrudRepository<JobApplication, String>
        implements JobApplicationRepository {

    private static final Logger LOG = Logger.getLogger(JobApplicationRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }
}
