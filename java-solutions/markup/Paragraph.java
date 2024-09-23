package markup;

import java.util.List;

public class Paragraph implements Markup {
    private final List<Markup> markups;

    public Paragraph(List<Markup> markups) {
        this.markups = markups;
    }

    @Override
    public void toMarkdown(StringBuilder output) {
        for (Markup markup : markups) {
            markup.toMarkdown(output);
        }
    }
}
