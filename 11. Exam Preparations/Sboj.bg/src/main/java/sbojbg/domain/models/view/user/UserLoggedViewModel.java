package sbojbg.domain.models.view.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbojbg.domain.entities.User;
import sbojbg.domain.models.view.Viewable;

@Getter
@Setter
@NoArgsConstructor
public class UserLoggedViewModel implements Viewable<User> {

    private String id;
    private String username;
}
