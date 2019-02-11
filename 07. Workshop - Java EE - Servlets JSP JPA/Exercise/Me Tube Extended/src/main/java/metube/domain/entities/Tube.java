package metube.domain.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tubes")
@NamedQuery(name = "Tube.findByTitle", query = "SELECT t FROM Tube t WHERE t.title = :title")
public class Tube extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 64)
    @Column(nullable = false, length = 64)
    private String title;

    @NotNull
    @Size(min = 2, max = 32)
    @Column(nullable = false, length = 32)
    private String author;

    @Size(max = 255)
    private String description;

    @Pattern(regexp = "[A-Za-z0-9-]{11}")
    @Column(name = "youtube_id", nullable = false, updatable = false, length = 11)
    private String youtubeId;

    @Min(0)
    private long views;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader", referencedColumnName = "id")
    private User uploader;
}
