package org.softuni.exam.web.beans.document;

import org.softuni.exam.domain.models.binding.document.DocumentScheduleBindingModel;
import org.softuni.exam.services.DocumentService;
import org.softuni.exam.web.beans.BaseBackingBean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ScheduleBacking extends BaseBackingBean {

    private final DocumentScheduleBindingModel model = new DocumentScheduleBindingModel();

    @Inject
    private DocumentService service;

    public void schedule() {
        service.schedule(model)
                .ifPresentOrElse(
                        id -> redirect("/details?id=" + id),
                        addMessageRunnable("Document scheduling failed. Please retry or contact support."));
    }

    public DocumentScheduleBindingModel getModel() {
        return model;
    }
}
