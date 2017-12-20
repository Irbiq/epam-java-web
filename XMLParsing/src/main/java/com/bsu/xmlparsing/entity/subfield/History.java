package com.bsu.xmlparsing.entity.subfield;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = " address ", propOrder = {
        "country", "year", "author"
})
public class History {

//    @XmlElement
    private String country;
//    @XmlElement
    private int year;
//    @XmlElement
    private String author;

    public History() {
    }

    public History(String country, int year, String author) {
        this.country = country;
        this.year = year;
        this.author = author;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History)) return false;

        History history = (History) o;

        if (year != history.year) return false;
        if (country != null ? !country.equals(history.country) : history.country != null) return false;
        return author != null ? author.equals(history.author) : history.author == null;
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "History{" +
                "country='" + country + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                '}';
    }
}
