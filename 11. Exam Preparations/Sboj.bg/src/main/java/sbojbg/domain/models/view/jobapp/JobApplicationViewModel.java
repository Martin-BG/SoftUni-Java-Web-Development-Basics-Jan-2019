package sbojbg.domain.models.view.jobapp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbojbg.domain.entities.JobApplication;
import sbojbg.domain.enums.Sector;
import sbojbg.domain.models.view.Viewable;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class JobApplicationViewModel implements Viewable<JobApplication> {

    private String id;
    private Sector sector;
    private String profession;
    private BigDecimal salary;
    private String description;
}
