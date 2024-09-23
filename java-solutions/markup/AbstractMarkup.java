package markup;

import java.util.List;

public abstract class AbstractMarkup implements Markup {
    private final List<Markup> markup;
    private final String symbol;

    public AbstractMarkup(List<Markup> markup, String symbol) {
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
