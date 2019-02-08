package metube.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TubeCreateBindingModel implements Bindable {

    @NotNull
    @Size(min = 2, max = 64)
    private String name;

    @Size(max = 255)
    private String description;

    @Pattern(regexp = "https://www.youtube.com/watch\\?v=[A-Za-z0-9-]{11}")
    private String youTubeLink;

    @NotNull
    @Size(min = 2, max = 32)
    private String uploader;
}
