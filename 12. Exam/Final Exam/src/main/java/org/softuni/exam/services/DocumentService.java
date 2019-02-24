package org.softuni.exam.services;

import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.binding.document.DocumentScheduleBindingModel;
import org.softuni.exam.domain.models.view.Viewable;

import java.util.Optional;

public interface DocumentService extends Service<Document, String> {

    Optional<String> schedule(DocumentScheduleBindingModel model);

    boolean print(String id);

    <M extends Viewable<Document>> Optional<M> findByTitle(String title, Class<M> clazz);
}
