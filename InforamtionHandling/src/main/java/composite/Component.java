package composite;

import exception.NotImplementedOperationException;


public interface Component<T> {
    T getChild();
    int getChildAmount();
    TextPartType getType();
    void addComponent(Component comp) throws NotImplementedOperationException;
    void removeComponent(Component comp) throws NotImplementedOperationException;
}
