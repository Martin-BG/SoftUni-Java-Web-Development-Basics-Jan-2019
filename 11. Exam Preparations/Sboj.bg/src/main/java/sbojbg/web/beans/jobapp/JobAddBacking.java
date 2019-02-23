package sbojbg.web.beans.jobapp;

import sbojbg.domain.models.binding.jobapp.JobApplicationAddBindingModel;
import sbojbg.services.JobApplicationService;
import sbojbg.web.beans.BaseBackingBean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class JobAddBacking extends BaseBackingBean {

    @Inject
    private JobApplicationService service;

    private JobApplicationAddBindingModel model = new JobApplicationAddBindingModel();

    public void addJob() {
        if (service.add(model)) {
            redirect("/home");
        } else {
            addMessage("Job Application create failed. Please try again or contact support.");
        }
    }

    public JobApplicationAddBindingModel getModel() {
        return model;
    }
}
