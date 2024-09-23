package markup;

public class Strikeout {
    private final Text text;

    public Strikeout(Text example) {
        text = example;

    }

    public void toMarkdown(StringBuilder res) {
        res.append("~");
        text.toMarkdown(res);
        res.append("~");
    }

}
