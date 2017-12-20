package com.bsu.xmlparsing.entity;

import com.bsu.xmlparsing.entity.subfield.constant.Valuable;
import com.bsu.xmlparsing.entity.subfield.History;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "OldCards")
public abstract class Card {

    @XmlAttribute(name = "id",required = true)
    protected int id;
    @XmlAttribute(name = "sent")
    protected boolean isSent;

    @XmlElement(name="history")
    protected History history = new History();
    @XmlElement(name = "theme")
    protected String theme;
    @XmlElement(name = "valuable")
    protected Valuable valuable;

    public Card() {
    }

    public Card(int id, History history, String theme, Valuable valuable, boolean isSent) {
        this.id = id;
        this.history = history;
        this.theme = theme;
        this.valuable = valuable;
        this.isSent = isSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Valuable getValuable() {
        return valuable;
    }

    public void setValuable(Valuable valuable) {
        this.valuable = valuable;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setIsSent(boolean sent) {
        isSent = sent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (id != card.id) return false;
        if (isSent != card.isSent) return false;
        if (history != null ? !history.equals(card.history) : card.history != null) return false;
        if (theme != null ? !theme.equals(card.theme) : card.theme != null) return false;
        return valuable == card.valuable;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isSent ? 1 : 0);
        result = 31 * result + (history != null ? history.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (valuable != null ? valuable.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", isSent=" + isSent +
                ", history=" + history +
                ", theme='" + theme + '\'' +
                ", valuable=" + valuable +
                '}';
    }
}
