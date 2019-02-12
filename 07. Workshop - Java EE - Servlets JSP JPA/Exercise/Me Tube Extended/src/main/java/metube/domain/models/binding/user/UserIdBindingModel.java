package metube.domain.models.binding.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metube.domain.entities.User;
import metube.domain.models.binding.Bindable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserIdBindingModel implements Bindable<User> {

    @NotNull
    @Size(min = 36, max = 36)
    private String id;
}
