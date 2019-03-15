package chushka.domain.entities.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        return type == null ? null : type.getLabel();
    }

    @Override
    public Type convertToEntityAttribute(String label) {
        return Type.fromLabel(label);
    }
}
