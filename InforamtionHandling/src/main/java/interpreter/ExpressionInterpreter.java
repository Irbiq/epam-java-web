package interpreter;

import interpreter.context.Context;
import interpreter.impl.*;
import interpreter.util.OperatorConstant;

import java.util.ArrayList;
import java.util.List;


public class ExpressionInterpreter {
    public static double interpret(List<String> expression) {
        ArrayList<AbstractInterpreter> expr = parse(expression);
        return evaluate(expr);
    }

    private static double evaluate(ArrayList<AbstractInterpreter> expression) {
        Context ctx = new Context();
        expression.forEach(e->e.interpret(ctx));
        return ctx.popValue();
    }

    private static ArrayList<AbstractInterpreter> parse(List<String> expressions) {
        ArrayList<AbstractInterpreter> expression = new ArrayList<>();
        for (String expr : expressions) {
            switch (expr) {
                case OperatorConstant.ADD:
                    expression.add(new AddInterpreter());
                    break;
                case OperatorConstant.SUB:
                    expression.add(new SubInterpreter());
                    break;
                case OperatorConstant.MUL:
                    expression.add(new MulInterpreter());
                    break;
                case OperatorConstant.DIV:
                    expression.add(new DivInterpreter());
                    break;
                default:
                    expression.add(new NumberInterpreter(Double.parseDouble(expr)));
            }
        }
        return expression;
    }
}
