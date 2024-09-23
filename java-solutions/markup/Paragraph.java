package markup;

import java.util.List;

public class Paragraph implements Markup {
    private final List<WrapableMarkup> markups;

    public Paragraph(List<WrapableMarkup> markups) {
        this.markups = markups;
    }

    @Override
    public void toMarkdown(StringBuilder output) {
        for (Markup markup : markups) {
            markup.toMarkdown(output);
        }
    }

    @Override
    public void toBBCode(StringBuilder output) {
        for (Markup markup : markups) {
            markup.toBBCode(output);
        }
    }
}
