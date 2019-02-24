package org.softuni.exam.repositories;

import org.softuni.exam.domain.entities.Document;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.util.Optional;
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
    public Optional<Document> findByTitle(String title) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery("Document.findByTitle", Document.class)
                    .setParameter("title", title)
                    .getSingleResult());
        } catch (IllegalStateException | PersistenceException e) {
            LOG.log(Level.SEVERE, "No Document found by title: " + title, e);
            return Optional.empty();
        }
    }
}
