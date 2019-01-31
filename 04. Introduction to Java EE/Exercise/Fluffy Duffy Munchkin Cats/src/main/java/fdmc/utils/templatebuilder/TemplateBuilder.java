package fdmc.utils.templatebuilder;

import java.util.Map;

public final class TemplateBuilder {

    private static final String TEMPLATE_FORMAT = "{{%s}}";

    private final StringBuilder stringBuilder;

    private TemplateBuilder(String initialContent) {
        stringBuilder = new StringBuilder(initialContent);
    }

    public static TemplateBuilder from(String initialContent) {
        return new TemplateBuilder(initialContent);
    }

    public TemplateBuilder put(String placeholder, String value) {
        String template = String.format(TEMPLATE_FORMAT, placeholder);
        int next;
        int prev = 0;
        while ((next = stringBuilder.indexOf(template, prev)) != -1) {
            stringBuilder.replace(next, next + template.length(), value);
            prev = next + value.length();
        }
        return this;
    }

    public TemplateBuilder put(Map<String, String> pairs) {
        pairs.forEach(this::put);
        return this;
    }

    public TemplateBuilder append(String content) {
        stringBuilder.append(content);
        return this;
    }

    public String build() {
        return stringBuilder.toString();
    }
}
