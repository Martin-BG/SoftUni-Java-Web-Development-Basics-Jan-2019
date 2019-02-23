package sbojbg.services;

import sbojbg.domain.entities.JobApplication;
import sbojbg.domain.models.binding.jobapp.JobApplicationAddBindingModel;

public interface JobApplicationService extends Service<JobApplication, String> {

    boolean add(JobApplicationAddBindingModel model);

    boolean delete(String id);
}
