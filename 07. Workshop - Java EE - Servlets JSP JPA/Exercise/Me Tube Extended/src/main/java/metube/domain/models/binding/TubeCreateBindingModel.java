package metube.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metube.domain.entities.Tube;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TubeCreateBindingModel implements Bindable<Tube> {

    @NotNull
    @Size(min = 2, max = 64)
    private String title;

    @NotNull
    @Size(min = 2, max = 32)
    private String author;

    @Size(max = 255)
    private String description;

    @Pattern(regexp = "[A-Za-z0-9-]{11}")
    private String youtubeId;

    @NotNull
    private UserIdBindingModel uploader;
}
