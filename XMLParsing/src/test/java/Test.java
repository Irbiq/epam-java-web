import com.bsu.xmlparsing.builder.CardDOMBuilder;
import com.bsu.xmlparsing.builder.CardSaxBuilder;
import com.bsu.xmlparsing.builder.JAXBBuilder;
import com.bsu.xmlparsing.entity.Card;
import com.bsu.xmlparsing.entity.GreetingCard;
import com.bsu.xmlparsing.entity.OrdinaryCard;
import com.bsu.xmlparsing.entity.subfield.History;
import com.bsu.xmlparsing.entity.subfield.constant.Valuable;
import com.bsu.xmlparsing.validator.SchemaValidator;
import com.bsu.xmlparsing.jaxbentity.CardType;
import com.bsu.xmlparsing.jaxbentity.HistoryType;
import com.bsu.xmlparsing.jaxbentity.OrdinaryCardType;
import org.testng.*;
import org.testng.annotations.BeforeTest;

import javax.xml.bind.JAXBException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private List<Card> expected;

    private List<CardType> expectedJaxb;
    private List<Card> real;
    private static final Path FILE_PATH = Paths.get("testinput", "test.xml");
    private static final Path FILE_PATH2 = Paths.get("input", "card.xml");
    @BeforeTest
    public void init() {
        expected = new ArrayList<>();
        expectedJaxb = new ArrayList<>();

        expected.add(new OrdinaryCard(1,new History("UA",1992,"MESSI"),"A", Valuable.COLLECTION,false));
        expected.add(new GreetingCard(1,new History("UA",1992,"MESSI"),"B", Valuable.COLLECTION,"aaaaaa",false));


        HistoryType history = new HistoryType();
        history.setAuthor("MESSI");
        history.setYear(BigInteger.valueOf(1992));
        history.setCountry("UA");
        CardType c1 = new OrdinaryCardType();

        c1.setId(BigInteger.valueOf(1));
        c1.setHistory(history);
        c1.setTheme("A");
        c1.setValuable(String.valueOf(Valuable.COLLECTION));
        c1.setSent(String.valueOf(false));

        expectedJaxb.add(c1);


    }

    @org.testng.annotations.Test
    public void schemaValidation(){

        Path file = Paths.get("input", "card.xml");
        Path schema = Paths.get("input", "card.xsd");
        SchemaValidator validator = new SchemaValidator();
        validator.setFilePath(file);
        validator.setSchemaPath(schema);

        Assert.assertTrue(validator.validate());
    }

    @org.testng.annotations.Test
    public void testStAX() {
        CardSaxBuilder builder= new CardSaxBuilder();
        builder.buildCardsList(FILE_PATH.toFile().getPath());
        real = builder.getCards();
        Assert.assertTrue(real.equals(expected));
    }

    @org.testng.annotations.Test
    public void testSax() {
        CardSaxBuilder builder = new CardSaxBuilder();
        builder.buildCardsList(FILE_PATH.toFile().getPath());
        real = builder.getCards();
        Assert.assertTrue(real.equals(expected));
    }

    @org.testng.annotations.Test
    public void testDOM() {
        CardDOMBuilder builder= new CardDOMBuilder();
        builder.buildCardsList(FILE_PATH.toFile().getPath());
        real = builder.getCards();
        Assert.assertTrue(real.equals(expected));
    }

    @org.testng.annotations.Test
    public void testJAXB() throws JAXBException {
        JAXBBuilder builder = new JAXBBuilder();
        List<CardType> cards = builder.buildCardsList(FILE_PATH2);
       cards.forEach(System.out::println);

    }

}
