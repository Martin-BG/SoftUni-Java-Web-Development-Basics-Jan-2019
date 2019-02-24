package org.softuni.exam.repositories;

import org.softuni.exam.domain.entities.Document;

import java.util.Optional;

public interface DocumentRepository extends CrudRepository<Document, String> {

    Optional<Document> findByTitle(String title);
}
