package sbojbg.domain.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SectorConverter implements AttributeConverter<Sector, String> {
    @Override
    public String convertToDatabaseColumn(Sector sector) {
        return sector == null ? null : sector.getName();
    }

    @Override
    public Sector convertToEntityAttribute(String name) {
        return Sector.fromName(name);
    }
}
