package markup;

import java.util.List;

public abstract class AbstractMarkup implements MarkupToMarkdown {
    private final List<MarkupToMarkdown> markup;
    private final String symbol;

    public AbstractMarkup(List<MarkupToMarkdown> markup, String symbol) {
        this.markup = markup;
        this.symbol = symbol;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append(symbol);
        for (MarkupToMarkdown markup : markup) {
            markup.toMarkdown(res);
        }

        res.append(symbol);
    }
}
