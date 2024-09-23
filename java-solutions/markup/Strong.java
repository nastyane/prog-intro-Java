package markup;

public class Strong implements MarkupToMarkdown {
    private final Text text;

    public Strong(Text example) {
        text = example;

    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append("__");
        text.toMarkdown(res);
        res.append("__");
    }

}
