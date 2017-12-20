package com.bsu.xmlparsing.entity;

import com.bsu.xmlparsing.entity.subfield.constant.Valuable;
import com.bsu.xmlparsing.entity.subfield.History;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OldCards")
public class GreetingCard extends Card {

    @XmlElement
    private String text;

    public GreetingCard() {
        this.history=new History();
    }

    public GreetingCard(int id, History history, String theme, Valuable valuable, String text, boolean isSent) {
        this.id = id;
        this.history = history;
        this.theme = theme;
        this.valuable = valuable;
        this.text = text;
        this.isSent = isSent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "GreetingCard{" +
                "text='" + text + '\'' +
                ", id=" + id +
                ", isSent=" + isSent +
                ", history=" + history +
                ", theme='" + theme + '\'' +
                ", valuable=" + valuable +
                '}';
    }
}
