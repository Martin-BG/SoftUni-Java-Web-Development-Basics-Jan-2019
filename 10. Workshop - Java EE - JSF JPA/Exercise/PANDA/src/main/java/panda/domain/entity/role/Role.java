package panda.domain.entity.role;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    USER("User"), ADMIN("Admin");

    private static final Map<String, Role> TITLE_TO_ROLE_MAP = Stream.of(Role.values())
            .collect(Collectors.toUnmodifiableMap(Role::getTitle, role -> role));
    private final String title;

    Role(String title) {
        this.title = title;
    }

    public static Role fromString(String role) {
        if (role == null || !TITLE_TO_ROLE_MAP.containsKey(role)) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        return TITLE_TO_ROLE_MAP.get(role);
    }

    public String getTitle() {
        return title;
    }
}
