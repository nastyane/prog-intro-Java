package markup;

import java.util.List;

public class Strong implements MarkupToMarkdown {
    private final List<MarkupToMarkdown> markup;

    public Strong(List<MarkupToMarkdown> markup) {
        this.markup = markup;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append("__");
        for (MarkupToMarkdown markup : markup) {
            markup.toMarkdown(res);
        }
        res.append("__");
    }

}
