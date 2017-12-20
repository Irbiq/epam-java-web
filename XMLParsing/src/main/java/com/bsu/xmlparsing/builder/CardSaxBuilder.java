package com.bsu.xmlparsing.builder;

import com.bsu.xmlparsing.entity.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class CardSaxBuilder {
    private static Logger logger = LogManager.getLogger();
    private List<Card> cards;
    private CardSaxHandler cardSaxHandler;
    private XMLReader reader;

    public CardSaxBuilder() {
        cardSaxHandler = new CardSaxHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(cardSaxHandler);
        } catch (SAXException e) {
            logger.error("Failed to create XMLReader", e);
        }
    }

    public void buildCardsList(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.error("Parsing error", e);
        } catch (IOException e) {
            logger.fatal(e);
        }
        cards = cardSaxHandler.getCards();
    }

    public List<Card> getCards() {
        return cards;
    }
}
