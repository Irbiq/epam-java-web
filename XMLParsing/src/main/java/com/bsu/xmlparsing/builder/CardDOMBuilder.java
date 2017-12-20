package com.bsu.xmlparsing.builder;

import com.bsu.xmlparsing.builder.constants.CardField;
import com.bsu.xmlparsing.entity.Card;
import com.bsu.xmlparsing.entity.GreetingCard;
import com.bsu.xmlparsing.entity.OrdinaryCard;
import com.bsu.xmlparsing.entity.subfield.constant.Valuable;
import com.bsu.xmlparsing.exception.IncorrectParameterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class CardDOMBuilder {
    private static Logger logger = LogManager.getLogger();
    private List<Card> cards;
    private DocumentBuilder documentBuilder;

    private static final int INDEX_NULL = 0;

    public CardDOMBuilder() {
        cards = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.fatal(e);
        }
    }


    public void buildCardsList(String fileName) {
        Document document;
        try {
            document = documentBuilder.parse(fileName);
            Element rootElement = document.getDocumentElement();
            NodeList ordinaryCardList = rootElement.getElementsByTagName("OrdinaryCard");
            NodeList greetingCardList = rootElement.getElementsByTagName("GreetingCard");
            Element ordinaryCard;
            Element greetingCard;
            for (int i = INDEX_NULL; i < ordinaryCardList.getLength(); i++) {
                ordinaryCard = (Element) ordinaryCardList.item(i);
                cards.add(buildOrdinaryCard(ordinaryCard));
            }
            for (int i = INDEX_NULL; i < greetingCardList.getLength(); i++) {
                greetingCard = (Element) greetingCardList.item(i);
                cards.add(buildGreetingCard(greetingCard));
            }
        } catch (IOException e) {
            logger.fatal("", e);
        } catch (SAXException e) {
            logger.error("Parsing error", e);
        } catch (IncorrectParameterException e) {
            logger.warn("", e);
        }
    }

    private OrdinaryCard buildOrdinaryCard(Element element) throws IncorrectParameterException {
        return (OrdinaryCard) buildCard(new OrdinaryCard(), element);
    }

    private GreetingCard buildGreetingCard(Element element) throws IncorrectParameterException {
        GreetingCard card = (GreetingCard) buildCard(new GreetingCard(), element);
        card.setText(getElementValue(element, CardField.TEXT.getValue()));
        return card;
    }

    private Card buildCard(Card card, Element cardElement) throws IncorrectParameterException {
        Element history = (Element) cardElement.getElementsByTagName(CardField.HISTORY.getValue()).item(INDEX_NULL);
        card.setId(Integer.parseInt(cardElement.getAttribute(CardField.ID.getValue())));
        card.setIsSent(Boolean.parseBoolean(cardElement.getAttribute(CardField.SENT.getValue())));
        card.setTheme(getElementValue(cardElement, CardField.THEME.getValue()));
        card.getHistory().setCountry(getElementValue(history, CardField.COUNTRY.getValue()));
        card.getHistory().setYear(Integer.parseInt(getElementValue(history, CardField.YEAR.getValue())));
        if (history.getElementsByTagName(CardField.AUTHOR.getValue()).item(0) != null) {
            card.getHistory().setAuthor(getElementValue(history, CardField.AUTHOR.getValue()));

        }
        if (cardElement.getElementsByTagName(CardField.VALUABLE.getValue()).item(0) != null) {
            card.setValuable(Valuable.valueOf(cardElement.getElementsByTagName(CardField.VALUABLE.getValue()).item(0).getTextContent().toUpperCase() ));
        }
        return card;
    }

    private static String getElementValue(Element parentElement, String tagName) {
        return parentElement.getElementsByTagName(tagName)
                            .item(INDEX_NULL)
                            .getTextContent();
    }

    public List<Card> getCards() {
        return cards;
    }
}
