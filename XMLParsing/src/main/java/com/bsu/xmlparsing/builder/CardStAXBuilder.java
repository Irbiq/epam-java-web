package com.bsu.xmlparsing.builder;

import com.bsu.xmlparsing.builder.constants.CardField;
import com.bsu.xmlparsing.entity.Card;
import com.bsu.xmlparsing.entity.GreetingCard;
import com.bsu.xmlparsing.entity.OrdinaryCard;
import com.bsu.xmlparsing.entity.subfield.constant.Valuable;
import com.bsu.xmlparsing.exception.IncorrectParameterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardStAXBuilder {
    private static Logger logger = LogManager.getLogger();
    private List<Card> cards;
    private XMLInputFactory inputFactory;

    public CardStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        cards = new ArrayList<>();
    }

    public void buildCards(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (CardField.GREETINGCARD.getValue().equals(name)) {
                        Card flower = buildCard(new GreetingCard(), reader);
                        cards.add(flower);
                    } else if (CardField.ORDINARYCARD.getValue().equals(name)) {
                        Card flower = buildCard(new OrdinaryCard(), reader);
                        cards.add(flower);
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("", e);
        } catch (FileNotFoundException e) {
            logger.fatal(e);
        } catch (IncorrectParameterException e) {
            logger.warn(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.fatal("", e);
            }
        }
    }

    private Card buildCard(Card card, XMLStreamReader reader) throws XMLStreamException, IncorrectParameterException {
        String name;
        CardField cardField;
        while (reader.hasNext()) {
            int type = reader.getEventType();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    cardField = CardField.valueOf(name.toUpperCase());
                    switch (cardField) {
                        case ORDINARYCARD:
                        case GREETINGCARD:
                            card.setId(Integer.parseInt(reader.getAttributeValue(null, CardField.ID.getValue())));
                            break;
                        /*case ID:
                            card.setId(SoilType.valueOf(getElementValue(reader).toUpperCase()));
                            break;*/
                        case SENT:
                            card.setIsSent(Boolean.parseBoolean(reader.getAttributeValue(null, CardField.SENT.getValue())));
                        case THEME:
                            card.setTheme(getElementValue(reader));
                            break;
                        case COUNTRY:
                            card.getHistory().setCountry(getElementValue(reader));
                            break;
                        case YEAR:
                            card.getHistory().setYear(Integer.parseInt(getElementValue(reader)));
                            break;
                        case AUTHOR:
                            card.getHistory().setAuthor(getElementValue(reader));
                            break;
                        case VALUABLE:
                            card.setValuable(Valuable.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                        case TEXT:
                            ((GreetingCard) card).setText(getElementValue(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    cardField = CardField.valueOf(name.toUpperCase());
                    if (cardField == CardField.GREETINGCARD || cardField == CardField.ORDINARYCARD) {
                        return card;
                    }
                    break;
            }
            reader.next();
        }
        throw new XMLStreamException("INCORRECT PARAMETER");
    }

    private String getElementValue(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    public List<Card> getCards() {
        return cards;
    }
}
