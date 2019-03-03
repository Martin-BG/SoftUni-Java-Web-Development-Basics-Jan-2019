package org.softuni.exam.repositories;

import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.view.document.DocumentShortViewModel;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, String> {

    List<DocumentShortViewModel> findAllShortView();
}
