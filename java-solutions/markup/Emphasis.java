package markup;

import java.util.List;

public class Emphasis implements MarkupToMarkdown {
    private final List<MarkupToMarkdown> markup;

    public Emphasis(List<MarkupToMarkdown> markup) {
        this.markup = markup;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append("*");
        for (MarkupToMarkdown markup : markup) {
            markup.toMarkdown(res);
        }

        res.append("*");
    }
}