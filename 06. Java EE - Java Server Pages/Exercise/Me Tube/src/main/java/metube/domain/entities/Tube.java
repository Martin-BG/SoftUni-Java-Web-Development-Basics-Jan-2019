package metube.domain.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tubes")
@NamedQueries({
        @NamedQuery(name = "Tube.findByName", query = "SELECT t FROM Tube t WHERE t.name = :name"),
        @NamedQuery(name = "Tube.findAllOrderByName", query = "SELECT t FROM Tube t ORDER BY t.name")
})
public class Tube extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 64)
    @Column(unique = true, nullable = false, length = 64)
    private String name;

    @Size(max = 255)
    private String description;

    @Pattern(regexp = "https://www.youtube.com/watch\\?v=[A-Za-z0-9-]{11}")
    @Column(name = "youtube_link", nullable = false, length = 43)
    private String youTubeLink;

    @NotNull
    @Size(min = 2, max = 32)
    @Column(nullable = false, length = 32)
    private String uploader;
}
