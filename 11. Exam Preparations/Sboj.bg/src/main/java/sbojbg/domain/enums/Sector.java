package sbojbg.domain.enums;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Sector {

    MEDICINE("Medicine"),
    CAR("Car"),
    FOOD("Food"),
    DOMESTIC("Domestic"),
    SECURITY("Security");

    private static final Map<String, Sector> LABEL_TO_SECTOR_MAP = Stream.of(Sector.values())
            .collect(Collectors.toUnmodifiableMap(Sector::getLabel, sector -> sector));

    private final String label;
    private final String imageFileName;

    Sector(String label) {
        this.label = label;
        imageFileName = name().toLowerCase(Locale.ENGLISH) + ".jpg";
    }

    public static Sector fromLabel(String label) {
        return label == null ? null : LABEL_TO_SECTOR_MAP.get(label);
    }

    public String getLabel() {
        return label;
    }

    public String getImageFileName() {
        return imageFileName;
    }
}
