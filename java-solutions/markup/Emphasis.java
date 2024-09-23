package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup {
    public Emphasis(List<WrapableMarkup> markup) {
        super(markup, "*");
    }
}