package sbojbg.web.beans.jobapp;

import sbojbg.domain.models.view.jobapp.JobApplicationViewModel;
import sbojbg.services.JobApplicationService;
import sbojbg.web.beans.BaseBackingBean;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.Map;

@Model
public class JobDetailsBacking extends BaseBackingBean {

    @Inject
    private JobApplicationService service;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    private JobApplicationViewModel model = new JobApplicationViewModel();

    @PostConstruct
    public void init() {
        String id = requestMap.get("id");
        model = service
                .findById(id, JobApplicationViewModel.class)
                .orElseThrow(() -> new NoResultException("Job Application not found"));
    }

    public JobApplicationViewModel getModel() {
        return model;
    }
}
