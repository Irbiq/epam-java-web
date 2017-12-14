package functional;

import composite.Component;
import composite.TextPartType;
import composite.impl.TextComponent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parser.TextParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextAnalyzerTest {

    private static String filePath = "input";
    private static String fileName = "input.txt";
    String textString = "";
    TextComponent text = new TextComponent(TextPartType.TEXT);
    TextParser textParser = new TextParser();


    @BeforeMethod
    public void setUp() throws Exception {
        try {
            textString = new String(Files.readAllBytes(Paths.get(filePath, fileName)));
            textParser.parse(text, textString, TextPartType.PARAGRAPH);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testSortByLexemeAmount() throws Exception {
        List<Component> sortedSentenceList = TextAnalyzer.sortByLexemeAmount(text);
        System.out.println("Sorted by lexeme amount  \n");
        printSentence(sortedSentenceList);
    }

    @Test
    public void testSortLexemes() throws Exception {
        List<Component> sortedWordsList = TextAnalyzer.sortLexemesByFirstChar(text);
        System.out.println("Sorted by 1st letter  \n");
        printWord(sortedWordsList);

    }

    @Test
    public void testSortLexemesByCharAmount() throws Exception {
        List<Component> sortedByChar = TextAnalyzer.sortLexemesByCharAmount(text, 'i');
        System.out.println("Sorted by char amount \n");
        printWord(sortedByChar);
    }

    public static void printSentence(List<Component> comps) {
        comps.forEach(System.out::println);
    }

    public static List<Component> printWord(List<Component> comps) {

        char fst = comps.get(0).toString().charAt(0);
        char ch;
        StringBuilder sb;
        List<String> result = new ArrayList<>();
        sb = new StringBuilder(comps.get(0) + " ");
        for (int i = 1; i < comps.size(); i++) {
            ch = comps.get(i).toString().charAt(0);
            if (fst == ch) {
                sb.append(comps.get(i)).append(" ");
            } else {
                result.add(sb.toString());
                sb = new StringBuilder();
                fst = ch;
            }
        }
        result.stream().filter(p -> !p.isEmpty()).forEach(System.out::println);
        return comps;
    }


}