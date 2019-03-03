package org.softuni.exam.repositories;

import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.view.document.DocumentShortViewModel;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class DocumentRepositoryImpl extends BaseCrudRepository<Document, String> implements DocumentRepository {

    private static final Logger LOG = Logger.getLogger(DocumentRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public List<DocumentShortViewModel> findAllShortView() {
        try {
            return entityManager
                    .createNamedQuery("Document.idAndTitle", DocumentShortViewModel.class)
                    .getResultList();
        } catch (IllegalStateException | PersistenceException e) {
            LOG.log(Level.SEVERE, "Retrieving of all Documents failed", e);
            return List.of();
        }
    }
}
