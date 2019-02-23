package sbojbg.services;

import sbojbg.domain.entities.JobApplication;
import sbojbg.domain.models.binding.jobapp.JobApplicationAddBindingModel;
import sbojbg.domain.models.view.Viewable;

import java.util.List;

public interface JobApplicationService extends Service<JobApplication, String> {

    boolean add(JobApplicationAddBindingModel model);

    boolean delete(String id);

    <M extends Viewable<JobApplication>> List<M> all(Class<M> clazz);
}
