package com.bsu.xmlparsing.entity;

import com.bsu.xmlparsing.entity.subfield.constant.Valuable;
import com.bsu.xmlparsing.entity.subfield.History;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OldCards")
public class OrdinaryCard extends Card {

    public OrdinaryCard() {
        this.history=new History();
    }

    public OrdinaryCard(int id, History history, String theme, Valuable valuable, boolean isSent) {
        this.id = id;
        this.history = history;
        this.theme = theme;
        this.valuable = valuable;
        this.isSent = isSent;
    }


    @Override
    public String toString() {
        return "OrdinaryCard{" +
                "id=" + id +
                ", isSent=" + isSent +
                ", history=" + history +
                ", theme='" + theme + '\'' +
                ", valuable=" + valuable +
                '}';
    }
}
