package functional;

import composite.TextPartType;
import composite.Component;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TextAnalyzer {
    private static List<Component> components = new ArrayList<>();

    public static List<Component> sortByLexemeAmount(Component component) {
        components.clear();
        collectComponents(component, TextPartType.SENTENCE);
        return components.stream()
                .sorted(Comparator.comparing(Component::getChildAmount))
                .collect(Collectors.toList());
    }

    public static List<Component> sortLexemesByFirstChar(Component component) {
        components.clear();
        collectComponents(component, TextPartType.WORD);
        return components.stream()
                .filter(p -> p.getType().ordinal() < TextPartType.PUNCTUATION.ordinal())
                .sorted(Comparator.comparingInt(c -> c.getChild().toString().toLowerCase().charAt(0)))
                .collect(Collectors.toList());
    }

    public static List<Component> sortLexemesByCharAmount(Component component,char ch) {
        components.clear();
        collectComponents(component, TextPartType.WORD);
        Comparator<Component> byCharThenReversedThenAlph = Comparator
                .comparing(c->c.getChild().toString().chars().filter(value -> (char)value==ch).count());
        byCharThenReversedThenAlph = byCharThenReversedThenAlph.reversed();
        byCharThenReversedThenAlph = byCharThenReversedThenAlph.thenComparing(c -> c.getChild().toString());
        return components.stream()
                .filter(p -> p.getType().ordinal() < TextPartType.PUNCTUATION.ordinal())
                .sorted( byCharThenReversedThenAlph)
                .collect(Collectors.toList());
    }

    private static void collectComponents(Component<List<Component>> component, TextPartType type) {
        if (component.getType().ordinal() < type.ordinal()) {
            List<Component> children = component.getChild();
            children.forEach(c -> collectComponents(c, type));
        } else {
            components.add(component);
        }
    }
}
