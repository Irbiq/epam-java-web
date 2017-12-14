package interpreter.impl;

import interpreter.AbstractInterpreter;
import interpreter.context.Context;

public class SubInterpreter implements AbstractInterpreter {
    @Override
    public void interpret(Context c) {
        double secondValue = c.popValue();
        double firstValue = c.popValue();
        c.pushValue(firstValue - secondValue);
    }
}
