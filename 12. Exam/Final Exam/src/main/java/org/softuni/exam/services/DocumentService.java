package org.softuni.exam.services;

import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.binding.document.DocumentScheduleBindingModel;
import org.softuni.exam.domain.models.view.document.DocumentShortViewModel;

import java.util.List;
import java.util.Optional;

public interface DocumentService extends Service<Document, String> {

    Optional<String> schedule(DocumentScheduleBindingModel model);

    boolean print(String id);

    List<DocumentShortViewModel> findAllShortView();
}
