package org.softuni.exam.repositories;

import org.softuni.exam.domain.entities.Document;

import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
public class DocumentRepositoryImpl extends BaseCrudRepository<Document, String> implements DocumentRepository {

    private static final Logger LOG = Logger.getLogger(DocumentRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }
}
