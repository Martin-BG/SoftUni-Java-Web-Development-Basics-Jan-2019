package metube.domain.models.view.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import metube.domain.entities.User;
import metube.domain.models.view.Viewable;
import metube.domain.models.view.tube.TubeUserViewModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileViewModel implements Viewable<User> {

    private String username;
    private String email;
    private List<TubeUserViewModel> tubes = new ArrayList<>();
}
