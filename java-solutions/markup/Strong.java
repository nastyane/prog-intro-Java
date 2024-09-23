package markup;

import java.util.List;

public class Strong extends AbstractMarkup {
    public Strong(List<WrapableMarkup> markup) {
        super(markup, "__", "b");
    }
}
