package org.softuni.exam.domain.models.view.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.view.Viewable;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DocumentDetailsViewModel implements Viewable<Document>, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String content;
}
