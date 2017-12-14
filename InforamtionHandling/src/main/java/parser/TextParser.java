package parser;

import composite.TextPartType;
import composite.Component;
import composite.impl.LeafComponent;
import composite.impl.LexemeComponent;
import composite.impl.TextComponent;
import exception.InterpreterException;
import exception.NotImplementedOperationException;
import interpreter.ExpressionInterpreter;
import interpreter.ExpressionResolver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TextParser {
    private static Logger logger = LogManager.getLogger(TextParser.class);

    private static final String BEGIN_PUNCT = "['].+";
    private static final String END_PUNCT = "[:punct:]";
    private static final String DIGITS = "\\d+";

    public void parse(Component component, String text, TextPartType type) {
        String[] parts = text.split(type.getRegex());
        if (type.ordinal() < TextPartType.LEXEME.ordinal()) {
            for (String p : parts) {
                if (!p.isEmpty()) {
                    TextComponent comp = new TextComponent(TextPartType.values()[type.ordinal()]);
                    try {
                        component.addComponent(comp);
                    } catch (NotImplementedOperationException e) {
                        logger.log(Level.ERROR, e);
                    }
                    parse(comp, p, TextPartType.values()[type.ordinal() + 1]);
                }
            }
        } else {
            Pattern mathExprPattern = Pattern.compile(DIGITS);
            for (String p : parts) {
                LexemeComponent lexemeComponent = new LexemeComponent();
                if (mathExprPattern.matcher(p).find()) {
                    try {
                        p = ExpressionResolver.resolveOperators(p);
                    } catch (InterpreterException e) {
                        logger.log(Level.ERROR, e);
                    }
                    double result = ExpressionInterpreter.interpret(ExpressionResolver.convertToPolishNotation(p));
                    try {
                        component.addComponent(lexemeComponent);
                    } catch (NotImplementedOperationException e) {
                        logger.log(Level.ERROR, e);
                    }
                    parseLexeme(lexemeComponent, String.valueOf(result));
                } else {
                    try {
                        component.addComponent(lexemeComponent);
                    } catch (NotImplementedOperationException e) {
                        logger.log(Level.ERROR, e);
                    }
                    parseLexeme(lexemeComponent, p);
                }
            }
        }
    }

    public static void parseLexeme(LexemeComponent lexeme, String s) {
        Pattern patternLeft = Pattern.compile(BEGIN_PUNCT);
        Pattern patternRight = Pattern.compile(END_PUNCT);
        if (patternLeft.matcher(s).matches()) {
            lexeme.addComponent(new LeafComponent("" + s.charAt(0), TextPartType.PUNCTUATION));
            s = s.substring(1);
        }
        int end = s.length() - 1;
        ArrayList<Character> punctEnds = new ArrayList<>();
        while (patternRight.matcher(s).matches()) {
            punctEnds.add(s.charAt(end));
            s = s.substring(0, end);
            end--;
        }
        lexeme.addComponent(new LeafComponent(s, TextPartType.WORD));
        punctEnds.forEach(p -> lexeme.addComponent(new LeafComponent(p.toString(), TextPartType.PUNCTUATION)));
    }


}
