package org.softuni.exam.domain.models.view.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.exam.domain.entities.User;
import org.softuni.exam.domain.models.view.Viewable;

@Getter
@Setter
@NoArgsConstructor
public class UserLoggedViewModel implements Viewable<User> {

    private String id;
    private String username;
}
