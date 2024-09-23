package markup;

import java.util.List;

public class Paragraph implements MarkupToMarkdown {
    private final List<MarkupToMarkdown> markups;

    public Paragraph(List<MarkupToMarkdown> markups) {
        this.markups = markups;
    }

    @Override
    public void toMarkdown(StringBuilder output) {
        for (MarkupToMarkdown markup : markups) {
            markup.toMarkdown(output);
        }
    }
}
