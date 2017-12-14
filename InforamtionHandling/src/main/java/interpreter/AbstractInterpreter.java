package interpreter;

import interpreter.context.Context;

@FunctionalInterface
public interface AbstractInterpreter {
    void interpret(Context c);
}
