package sbojbg.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbojbg.domain.enums.Sector;
import sbojbg.domain.enums.SectorConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "job_applications")
public class JobApplication extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    @Convert(converter = SectorConverter.class)
    private Sector sector;

    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String profession;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal salary;

    private String description;
}
