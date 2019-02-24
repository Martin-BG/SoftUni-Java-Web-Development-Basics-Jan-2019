package org.softuni.exam.web.beans.document;

import org.softuni.exam.domain.models.view.document.DocumentDetailsViewModel;
import org.softuni.exam.services.DocumentService;
import org.softuni.exam.web.beans.BaseBackingBean;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.Map;

@Model
public class DetailsBacking extends BaseBackingBean {

    private DocumentDetailsViewModel model = new DocumentDetailsViewModel();

    @Inject
    private DocumentService service;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        String id = requestMap.get("id");
        model = service
                .findById(id, DocumentDetailsViewModel.class)
                .orElseThrow(() -> new NoResultException("Document not found"));
    }

    public DocumentDetailsViewModel getModel() {
        return model;
    }
}
