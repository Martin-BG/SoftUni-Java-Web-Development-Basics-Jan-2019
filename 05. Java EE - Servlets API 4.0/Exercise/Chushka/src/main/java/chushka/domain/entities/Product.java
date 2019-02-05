package chushka.domain.entities;

import chushka.domain.entities.enums.Type;
import chushka.domain.entities.enums.TypeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "products")
public class Product extends BaseEntity {

    @Column(unique = true, nullable = false)
    @Size(min = 1)
    private String name;

    private String description;

    @Column(nullable = false)
    @Convert(converter = TypeConverter.class)
    private Type type;
}
