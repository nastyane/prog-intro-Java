package markup;

public class Strikeout implements MarkupToMarkdown {
    private final MarkupToMarkdown markup;

    public Strikeout(MarkupToMarkdown markup) {
        this.markup = markup;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append("~");
        markup.toMarkdown(res);
        res.append("~");
    }

}
