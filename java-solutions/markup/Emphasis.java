package markup;

public class Emphasis implements MarkupToMarkdown {
    private final Text text;

    public Emphasis(Text example) {
        text = example;

    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append("*");
        text.toMarkdown(res);
        res.append("*");
    }
}