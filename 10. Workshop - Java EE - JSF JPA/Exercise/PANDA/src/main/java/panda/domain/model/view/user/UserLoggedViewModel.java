package panda.domain.model.view.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import panda.domain.api.Viewable;
import panda.domain.entity.User;
import panda.domain.entity.role.Role;

@Getter
@NoArgsConstructor
public class UserLoggedViewModel implements Viewable<User> {

    private String id;
    private String username;
    private Role role;
}
