package markup;

import java.util.List;

public class Strong extends AbstractMarkup {
    public Strong(List<Markup> markup) {
        super(markup, "__");
    }
}
