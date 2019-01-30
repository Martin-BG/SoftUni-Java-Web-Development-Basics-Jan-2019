package fdmc.utils;

import java.util.Map;
import java.util.Optional;

public interface Reader {

    Optional<String> read(String path);

    Optional<Map<String, String>> read(Map<String, String> uris);
}
