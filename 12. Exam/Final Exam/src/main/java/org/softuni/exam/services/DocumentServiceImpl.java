package org.softuni.exam.services;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.binding.document.DocumentScheduleBindingModel;
import org.softuni.exam.domain.models.view.document.DocumentShortViewModel;
import org.softuni.exam.repositories.DocumentRepository;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DocumentServiceImpl extends BaseService<Document, String, DocumentRepository> implements DocumentService {

    private static final Logger LOG = Logger.getLogger(DocumentServiceImpl.class.getName());

    @Inject
    public DocumentServiceImpl(DocumentRepository repository,
                               ModelMapper mapper,
                               Validator validator) {
        super(mapper, validator, repository);
    }

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public Optional<String> schedule(DocumentScheduleBindingModel model) {
        return createAndReturn(model).map(Document::getId);
    }

    @Override
    public boolean print(String id) {
        return repository.delete(id);
    }

    private static String getShortTitle(String title) {
        int maxTitleLength = 15;
        if (title.length() > maxTitleLength) {
            return title.substring(0, maxTitleLength - 3) + "...";
        }
        return title;
    }

    @Override
    public List<DocumentShortViewModel> findAllShortView() {
        List<DocumentShortViewModel> documents = repository.findAllShortView();
        documents.forEach(d -> d.setTitle(getShortTitle(d.getTitle())));
        return documents;
    }
}
