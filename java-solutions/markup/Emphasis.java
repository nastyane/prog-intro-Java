package markup;

public class Emphasis implements MarkupToMarkdown {
    private final MarkupToMarkdown markup;

    public Emphasis(MarkupToMarkdown markup) {
        this.markup = markup;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append("*");
        markup.toMarkdown(res);
        res.append("*");
    }
}