package sbojbg.domain.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SectorConverter implements AttributeConverter<Sector, String> {
    @Override
    public String convertToDatabaseColumn(Sector sector) {
        return sector == null ? null : sector.getLabel();
    }

    @Override
    public Sector convertToEntityAttribute(String label) {
        return Sector.fromLabel(label);
    }
}
