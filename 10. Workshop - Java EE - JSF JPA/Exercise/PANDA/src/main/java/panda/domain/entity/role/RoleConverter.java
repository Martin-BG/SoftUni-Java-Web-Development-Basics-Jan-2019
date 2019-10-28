package panda.domain.entity.role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        return role == null ? null : role.getTitle();
    }

    @Override
    public Role convertToEntityAttribute(String role) {
        return Role.fromString(role);
    }
}
