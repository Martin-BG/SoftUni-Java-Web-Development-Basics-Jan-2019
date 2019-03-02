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

    private static String getShortTitle(String title) {
        int maxTitleLength = 15;
        if (title.length() > maxTitleLength) {
            return title.substring(0, maxTitleLength - 3) + "...";
        }
        return title;
    }

    @PostConstruct
    private void init() {
        documents = service.findAll(DocumentShortViewModel.class);
        documents.forEach(d -> d.setTitle(getShortTitle(d.getTitle())));
    }

    public List<DocumentShortViewModel> getDocuments() {
        return Collections.unmodifiableList(documents);
    }
}
