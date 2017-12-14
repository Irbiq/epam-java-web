package composite.impl;

import composite.Component;
import composite.TextPartType;

import java.util.LinkedList;
import java.util.List;


public class LexemeComponent implements Component<List<Component>> {
    private List<Component> components = new LinkedList<>();

    @Override
    public TextPartType getType() {
        return TextPartType.LEXEME;
    }

    @Override
    public List<Component> getChild() {
        return components;
    }

    @Override
    public int getChildAmount() {
        return components.size();
    }

    @Override
    public void addComponent(Component c) {
        components.add(c);
    }

    @Override
    public void removeComponent(Component c) {
        components.remove(c);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        components.forEach(c -> s.append(c.toString()));
        s.append(TextPartType.LEXEME.getEnd());
        return s.toString();
    }
}
