package chushka.domain.entities.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        return type == null ? null : type.getName();
    }

    @Override
    public Type convertToEntityAttribute(String name) {
        return Type.fromName(name);
    }
}
