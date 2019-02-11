package metube.domain.models.view.tube;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metube.domain.entities.Tube;
import metube.domain.models.view.Viewable;

@Getter
@Setter
@NoArgsConstructor
public class TubeUserViewModel implements Viewable<Tube> {

    private String id;
    private String title;
    private String author;
}
