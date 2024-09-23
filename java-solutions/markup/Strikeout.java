package markup;

public class Strikeout implements MarkupToMarkdown {
    private final Text text;

    public Strikeout(Text example) {
        text = example;

    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append("~");
        text.toMarkdown(res);
        res.append("~");
    }

}
