package interpreter.impl;

import interpreter.AbstractInterpreter;
import interpreter.context.Context;

public class NumberInterpreter implements AbstractInterpreter {
    private double number;

    public NumberInterpreter(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
