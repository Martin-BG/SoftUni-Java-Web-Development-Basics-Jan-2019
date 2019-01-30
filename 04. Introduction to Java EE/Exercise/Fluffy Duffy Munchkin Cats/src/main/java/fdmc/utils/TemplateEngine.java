package fdmc.utils;

import java.util.Map;

public class TemplateEngine {

    private static final String TEMPLATE_FORMAT = "{{%s}}";

    public String applyTemplates(String text, Map<String, String> templateMap) {
        StringBuilder stringBuilder = new StringBuilder(text);
        templateMap.forEach((placeholder, value) -> {
            String template = String.format(TEMPLATE_FORMAT, placeholder);
            int next;
            int prev = 0;
            while ((next = stringBuilder.indexOf(template, prev)) != -1) {
                stringBuilder.replace(next, next + template.length(), value);
                prev = next + value.length();
            }
        });
        return stringBuilder.toString();
    }
}
