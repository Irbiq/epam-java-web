package composite.impl;


import composite.Component;
import composite.TextPartType;
import exception.NotImplementedOperationException;



public class LeafComponent implements Component<String> {
    private String component;
    private TextPartType type;

    public LeafComponent(String component, TextPartType type) {
        this.component = component;
        this.type = type;
    }

    @Override
    public TextPartType getType() {
        return type;
    }

    @Override
    public String getChild() {
        return component;
    }

    @Override
    public int getChildAmount() {
        return 1;
    }

    @Override
    public void addComponent(Component c) throws NotImplementedOperationException {
        throw new NotImplementedOperationException();
    }

    @Override
    public void removeComponent(Component c) throws NotImplementedOperationException {
        throw new NotImplementedOperationException();
    }

    @Override
    public String toString() {
        return component;
    }
}
