package markup;

import java.util.List;

public abstract class AbstractMarkup implements WrapableMarkup {
    private final List<WrapableMarkup> markup;
    private final String symbol;

    public AbstractMarkup(List<WrapableMarkup> markup, String symbol) {
        this.markup = markup;
        this.symbol = symbol;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append(symbol);
        for (Markup markup : markup) {
            markup.toMarkdown(res);
        }

        res.append(symbol);
    }
}
