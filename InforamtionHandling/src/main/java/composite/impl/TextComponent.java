package composite.impl;


import composite.Component;
import composite.TextPartType;

import java.util.LinkedList;
import java.util.List;


public class TextComponent implements Component<List<Component>> {

    private List<Component> components = new LinkedList<>();
    private TextPartType type;

    public TextComponent(TextPartType type) {
        this.type = type;
    }

    public TextComponent(Component component) {
        this.components = (List<Component>) component.getChild();
        this.type = component.getType();
    }

    @Override
    public TextPartType getType() {
        return type;
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
        s.append(type.getBegin());
        components.forEach(part -> s.append(part.toString()));
        s.append(type.getEnd());
        return s.toString();
    }
}
