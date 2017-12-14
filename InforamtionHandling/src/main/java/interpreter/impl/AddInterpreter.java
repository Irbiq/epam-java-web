package interpreter.impl;

import interpreter.AbstractInterpreter;
import interpreter.context.Context;

public class AddInterpreter implements AbstractInterpreter {
    @Override
    public void interpret(Context c) {
        double firstValue = c.popValue();
        double secondValue = c.popValue();
        c.pushValue(firstValue + secondValue);
    }
}
