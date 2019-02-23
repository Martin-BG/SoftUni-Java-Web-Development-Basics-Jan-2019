package sbojbg.services;

import org.modelmapper.ModelMapper;
import sbojbg.domain.entities.JobApplication;
import sbojbg.domain.models.binding.jobapp.JobApplicationAddBindingModel;
import sbojbg.domain.models.view.Viewable;
import sbojbg.repositories.JobApplicationRepository;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;
import java.util.logging.Logger;

public class JobApplicationServiceImpl extends BaseService<JobApplication, String, JobApplicationRepository>
        implements JobApplicationService {

    private static final Logger LOG = Logger.getLogger(JobApplicationServiceImpl.class.getName());

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository repository,
                                     ModelMapper mapper,
                                     Validator validator) {
        super(mapper, validator, repository);
    }

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public boolean add(JobApplicationAddBindingModel model) {
        return create(model);
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public <M extends Viewable<JobApplication>> List<M> all(Class<M> clazz) {
        return findAll(clazz);
    }
}
