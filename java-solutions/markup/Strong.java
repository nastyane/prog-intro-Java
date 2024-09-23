package markup;

public class Strong implements MarkupToMarkdown {
    private final MarkupToMarkdown markup;

    public Strong(MarkupToMarkdown markup) {
        this.markup = markup;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append("__");
        markup.toMarkdown(res);
        res.append("__");
    }

}
