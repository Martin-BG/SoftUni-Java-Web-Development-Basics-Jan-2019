package fdmc.utils.htmlbuilder;

import java.util.Map;
import java.util.Optional;

public interface HtmlBuilder {

    Optional<String> buildFrom(String baseTemplateUri,
                               Map<String, String> templatesUris,
                               Map<String, String> params);

    Optional<String> buildFrom(Map<String, String> templatesUris,
                               Map<String, String> params);

    Optional<String> buildFrom(String baseTemplateUri,
                               Map<String, String> params);

    Optional<String> buildFrom(Map<String, String> params);

    Optional<String> buildFrom(String baseTemplateUri);
}
