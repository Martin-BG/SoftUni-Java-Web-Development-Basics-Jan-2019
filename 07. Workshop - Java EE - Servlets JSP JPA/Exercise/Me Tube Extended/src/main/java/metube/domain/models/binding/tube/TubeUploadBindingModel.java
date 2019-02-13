package metube.domain.models.binding.tube;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metube.domain.entities.Tube;
import metube.domain.models.binding.Bindable;
import metube.domain.models.binding.user.UserIdBindingModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TubeUploadBindingModel implements Bindable<Tube> {

    @NotNull
    @Size(min = 2, max = 64)
    private String title;

    @NotNull
    @Size(min = 2, max = 32)
    private String author;

    @Size(max = 255)
    private String description;

    @NotNull
    @Pattern(regexp = "[A-Za-z0-9-]{11}")
    private String youtubeId;

    @NotNull
    private UserIdBindingModel uploader;
}
