package markup;

public class Emphasis {
    private final Text text;

    public Emphasis(Text example) {
        text = example;

    }

    public void toMarkdown(StringBuilder res) {
        res.append("*");
        text.toMarkdown(res);
        res.append("*");
    }

}