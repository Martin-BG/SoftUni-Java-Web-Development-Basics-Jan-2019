package metube.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metube.domain.entities.Tube;

@Getter
@Setter
@NoArgsConstructor
public class TubeViewModel implements Viewable<Tube> {

    private String name;
    private String description;
    private String youTubeLink;
    private String uploader;
}
