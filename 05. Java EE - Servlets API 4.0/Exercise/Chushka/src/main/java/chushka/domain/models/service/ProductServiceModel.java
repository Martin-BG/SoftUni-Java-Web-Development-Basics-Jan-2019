package chushka.domain.models.service;

import chushka.domain.entities.enums.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductServiceModel {

    private String id;
    private String name;
    private String description;
    private Type type;
}
