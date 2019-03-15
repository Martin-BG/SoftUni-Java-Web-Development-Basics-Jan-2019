package chushka.domain.entities.enums;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Type {

    FOOD("Food"),
    DOMESTIC("Domestic"),
    HEALTH("Health"),
    COSMETIC("Cosmetic"),
    OTHER("Other");

    private static final Map<String, Type> LABEL_TO_ENUM_MAP = Stream.of(Type.values())
            .collect(Collectors.toUnmodifiableMap(Type::getLabel, type -> type));

    private final String label;

    Type(String label) {
        this.label = label;
    }

    public static Type fromLabel(String label) {
        return label == null ? null : LABEL_TO_ENUM_MAP.get(label);
    }

    public String getLabel() {
        return label;
    }
}
