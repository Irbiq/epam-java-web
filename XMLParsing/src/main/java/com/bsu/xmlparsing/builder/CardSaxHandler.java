package com.bsu.xmlparsing.builder;

import com.bsu.xmlparsing.builder.constants.CardField;
import com.bsu.xmlparsing.entity.Card;
import com.bsu.xmlparsing.entity.GreetingCard;
import com.bsu.xmlparsing.entity.OrdinaryCard;
import com.bsu.xmlparsing.entity.subfield.constant.Valuable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CardSaxHandler extends DefaultHandler {

    private List<Card> cards;
    private Card currentCard;
    private CardField currentElement = CardField.EMPTY_TAG;

    public CardSaxHandler() {
        cards = new ArrayList<>();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        CardField param = CardField.valueOf(localName.toUpperCase());
        String isSent;
        switch (param) {
            case ORDINARYCARD:
                currentCard = new OrdinaryCard();
                setIdAttr(attributes);
                if ((isSent = attributes.getValue(CardField.SENT.getValue())) != null) {
                    currentCard.setIsSent(Boolean.parseBoolean(isSent));
                }
                //currentElement = CardField.EMPTY_TAG;
                break;
            case GREETINGCARD:
                currentCard = new GreetingCard();
                setIdAttr(attributes);
                if ((isSent = attributes.getValue(CardField.SENT.getValue())) != null) {
                    currentCard.setIsSent(Boolean.parseBoolean(isSent));
                }
                //currentElement = CardField.EMPTY_TAG;
                break;
            case CARD:
            case OLDCARDS:
            case HISTORY:
                currentElement = CardField.EMPTY_TAG;
                break;
            default:
                currentElement = CardField.valueOf(localName.toUpperCase());
        }
    }

    private void setIdAttr(Attributes attributes) {
        currentCard.setId(Integer.parseInt(attributes.getValue(CardField.ID.getValue())));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (CardField.ORDINARYCARD.getValue().equals(localName) || CardField.GREETINGCARD.getValue().equals(localName)) {
            cards.add(currentCard);
        }
        currentElement = CardField.EMPTY_TAG;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length).trim();
        if (currentElement != CardField.EMPTY_TAG) {

            switch (currentElement) {
                case THEME:
                    currentCard.setTheme(text);
                    break;
                case COUNTRY:
                    currentCard.getHistory().setCountry(text);
                    break;
                case YEAR:
                    currentCard.getHistory().setYear(Integer.parseInt(text));
                    break;
                case AUTHOR:
                    currentCard.getHistory().setAuthor(text);
                    break;
                case VALUABLE:
                    currentCard.setValuable(Valuable.valueOf(text.toUpperCase()));
                    break;
                case TEXT:
                    ((GreetingCard) currentCard).setText(text);
                    break;
                default:
                    throw new SAXException("No such tag exception");
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}


