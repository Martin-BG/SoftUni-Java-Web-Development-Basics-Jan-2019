package metube.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TubeViewModel {

    private String name;
    private String description;
    private String youTubeLink;
    private String uploader;
}
