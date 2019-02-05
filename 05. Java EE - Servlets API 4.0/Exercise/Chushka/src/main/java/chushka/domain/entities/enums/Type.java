package chushka.domain.entities.enums;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Type {
    FOOD("Food"),
    DOMESTIC("Domestic"),
    HEALTH("Health"),
    COSMETIC("Cosmetic"),
    OTHER("Other");

    private static final Map<String, Type> TYPE_MAP = Stream.of(Type.values())
            .collect(Collectors.toUnmodifiableMap(type -> type.name(), type -> type));

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public static Type fromName(String name) {
        return name == null ? null : TYPE_MAP.get(name.toUpperCase(Locale.ENGLISH));
    }

    public String getName() {
        return name;
    }
}
