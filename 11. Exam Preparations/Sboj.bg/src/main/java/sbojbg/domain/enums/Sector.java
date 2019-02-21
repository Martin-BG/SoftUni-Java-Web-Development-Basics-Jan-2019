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

    private static final Map<String, Sector> TYPE_MAP = Stream.of(Sector.values())
            .collect(Collectors.toUnmodifiableMap(Enum::name, type -> type));

    private final String name;

    Sector(String name) {
        this.name = name;
    }

    public static Sector fromName(String name) {
        return name == null ? null : TYPE_MAP.get(name.toUpperCase(Locale.ENGLISH));
    }

    public String getName() {
        return name;
    }
}
