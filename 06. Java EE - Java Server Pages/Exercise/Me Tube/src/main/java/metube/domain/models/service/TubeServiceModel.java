package metube.domain.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TubeServiceModel {

    private String id;
    private String name;
    private String description;
    private String youTubeLink;
    private String uploader;
}
