package com.bsu.xmlparsing.builder;

import com.bsu.xmlparsing.jaxbentity.CardType;
import com.bsu.xmlparsing.jaxbentity.ObjectFactory;
import com.bsu.xmlparsing.jaxbentity.OldCardsType;
import org.w3c.dom.DocumentType;

import javax.xml.bind.*;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class JAXBBuilder {

    public static List<CardType> buildCardsList(Path path ) throws JAXBException {
        File xml = path.toFile();
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        OldCardsType cards = (OldCardsType) ((JAXBElement<DocumentType>) jaxbContext.createUnmarshaller().unmarshal(xml)).getValue();
        return cards.getOrdinaryCardOrGreetingCardOrCard();

    }
}
