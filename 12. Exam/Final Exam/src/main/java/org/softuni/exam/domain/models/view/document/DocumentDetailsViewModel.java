package org.softuni.exam.domain.models.view.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.view.Viewable;

@Getter
@Setter
@NoArgsConstructor
public class DocumentDetailsViewModel implements Viewable<Document> {

    private String id;
    private String title;
    private String content;
}
