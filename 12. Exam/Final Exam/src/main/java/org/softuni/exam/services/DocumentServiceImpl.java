package org.softuni.exam.services;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.binding.document.DocumentScheduleBindingModel;
import org.softuni.exam.domain.models.view.Viewable;
import org.softuni.exam.repositories.DocumentRepository;

import javax.inject.Inject;
import javax.validation.Validator;
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

    @Override
    public <M extends Viewable<Document>> Optional<M> findByTitle(String title, Class<M> clazz) {
        return repository
                .findByTitle(title)
                .map(e -> mapper.map(e, clazz));
    }
}
