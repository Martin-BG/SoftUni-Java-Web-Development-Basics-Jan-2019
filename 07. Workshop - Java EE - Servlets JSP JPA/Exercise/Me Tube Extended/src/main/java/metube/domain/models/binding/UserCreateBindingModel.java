package metube.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metube.domain.entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateBindingModel implements Bindable<User> {

    @NotNull
    @Size(min = 1, max = 32)
    private String username;

    @NotNull
    @Size(min = 1, max = 32)
    private String password;

    @NotNull
    @Size(min = 1, max = 32)
    private String confirmPassword;

    @NotNull
    @Size(min = 1, max = 64)
    private String email;
}
