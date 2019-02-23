package sbojbg.web.beans.user;

import sbojbg.domain.models.view.jobapp.JobApplicationViewModel;
import sbojbg.services.JobApplicationService;
import sbojbg.web.beans.BaseBackingBean;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Model
public class HomeBacking extends BaseBackingBean {

    @Inject
    private JobApplicationService service;

    private List<JobApplicationViewModel> jobs = List.of();

    @PostConstruct
    private void init() {
        jobs = service.all(JobApplicationViewModel.class);
    }

    public List<JobApplicationViewModel> getJobs() {
        return Collections.unmodifiableList(jobs);
    }
}
