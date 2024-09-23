package markup;

import java.util.List;

public abstract class AbstractMarkup implements WrapableMarkup {
    private final List<WrapableMarkup> markup;
    private final String symbol;
    private final String bbcode;

    public AbstractMarkup(List<WrapableMarkup> markup, String symbol, String bbcode) {
        this.markup = markup;
        this.symbol = symbol;
        this.bbcode = bbcode;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append(symbol);
        for (Markup markup : markup) {
            markup.toMarkdown(res);
        }
        res.append(symbol);
    }

    @Override
    public void toBBCode(StringBuilder res) {
        res.append("[").append(bbcode).append("]");
        for (Markup markup : markup) {
            markup.toBBCode(res);
        }
        res.append("[/").append(bbcode).append("]");
    }
}
