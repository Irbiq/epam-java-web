package composite;


public enum TextPartType {
    TEXT{

    },
    PARAGRAPH{
        {
            this.regex ="(?:\\t)|(?:[^\\^]\\t)(?=\\$)?";
            this.begin="\t";
            this.end="\n";
        }
    },
    SENTENCE{
        {
            this.regex="(?<=\\.)\\s(?=[A-Z])";
        }
    },
    LEXEME{
        {
            this.regex= "\\s";
            this.end=" ";
        }
    },
    WORD,
    PUNCTUATION;

    String regex;
    String end = "";
    String begin = "";

    public String getRegex() {
        return regex;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

}
