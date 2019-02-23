package sbojbg.web.beans.jobapp;

import sbojbg.domain.models.view.jobapp.JobApplicationViewModel;
import sbojbg.services.JobApplicationService;
import sbojbg.web.beans.BaseBackingBean;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@Model
public class JobDeleteBacking extends BaseBackingBean {

    @Inject
    private JobApplicationService service;

    @Inject
    private HttpServletRequest request;

    private JobApplicationViewModel model = new JobApplicationViewModel();

    @PostConstruct
    public void init() {
        String id = request.getParameter("id");
        model = service
                .findById(id, JobApplicationViewModel.class)
                .orElseThrow(() -> new NoResultException("Job Application not found"));
    }

    public void deleteJob() {
        if (service.delete(request.getParameter("id"))) {
            redirect("/home");
        } else {
            addMessage("Job Application delete failed. Please try again or contact support.");
        }
    }

    public JobApplicationViewModel getModel() {
        return model;
    }
}
