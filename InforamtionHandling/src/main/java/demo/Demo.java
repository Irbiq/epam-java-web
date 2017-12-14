package demo;

import composite.TextPartType;
import composite.Component;
import composite.impl.TextComponent;
import functional.TextAnalyzer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.TextParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    private static Logger logger = LogManager.getLogger(Demo.class);
    private static String filePath = "input";
    private static String fileName = "input.txt";

    public static void main(String[] args) {
        String textString = "";
        try {
            textString = new String(Files.readAllBytes(Paths.get(filePath, fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextComponent text = new TextComponent(TextPartType.TEXT);
        TextParser textComponentParser = new TextParser();
        textComponentParser.parse(text, textString, TextPartType.PARAGRAPH);

        logger.log(Level.DEBUG, text.toString());

        List<Component> sortedSentenceList = TextAnalyzer.sortByLexemeAmount(text);
        logComponentList("Sorted by lexeme amount  \n", sortedSentenceList);

        List<Component> sortedWordsList = TextAnalyzer.sortLexemesByFirstChar(text);
        logWordList("Sorted by 1st letter  \n", sortedWordsList);

        List<Component> sortedByChar = TextAnalyzer.sortLexemesByCharAmount(text,'i');
        logWordList("Sorted by char amount  \n", sortedByChar);


    }

    public static void logComponentList(String message, List<Component> list) {
        logger.log(Level.DEBUG, message);
        list.forEach(p -> logger.log(Level.DEBUG, p));
    }

    public static List<Component> logWordList(String message, List<Component> list) {
        logger.log(Level.DEBUG, message);

        char c1 = list.get(0).toString().toLowerCase().charAt(0);
        char c2;
        String temp;
        List<String> result = new ArrayList<>();
        temp = list.get(0) + " ";
        for (int i = 1; i < list.size(); i++) {
            c2 = list.get(i).toString().toLowerCase().charAt(0);
            if (c1 == c2) {
                temp += list.get(i) + " ";
            } else {
                result.add(temp);
                temp = "";
                c1 = c2;
            }
        }

        result.stream().filter(p -> !p.isEmpty()).forEach(p -> logger.log(Level.DEBUG, p));
        return list;
    }
}
