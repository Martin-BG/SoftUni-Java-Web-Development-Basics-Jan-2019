package org.softuni.exam.web.beans.user;

import org.softuni.exam.domain.models.view.document.DocumentShortViewModel;
import org.softuni.exam.services.DocumentService;
import org.softuni.exam.web.beans.BaseBackingBean;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Model
public class HomeBacking extends BaseBackingBean {

    @Inject
    private DocumentService service;

    private List<DocumentShortViewModel> documents = List.of();

    @PostConstruct
    private void init() {
        documents = service.findAllShortView();
    }

    public List<DocumentShortViewModel> getDocuments() {
        return Collections.unmodifiableList(documents);
    }
}
