package org.softuni.exam.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "documents")
@NamedQuery(name = "Document.findByTitle",
        query = "SELECT d FROM Document d WHERE d.title = :title")
public class Document extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 255)
    @Column(unique = true, nullable = false)
    private String title;

    @NotNull
    @Size(min = 1)
    @Lob
    @Column(nullable = false)
    private String content;
}
