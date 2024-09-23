package markup;

import java.util.List;

public class Strong extends AbstractMarkup {
    public Strong(List<MarkupToMarkdown> markup) {
        super(markup, "__");
    }
}
