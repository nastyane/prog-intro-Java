package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup {
    public Strikeout(List<WrapableMarkup> markup) {
        super(markup, "~", "s");
    }
}
