package markup;

public class Text implements WrapableMarkup {
    private final String text;

    public Text(String example) {
        text = example;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append(text);
    }

    @Override
    public void toBBCode(StringBuilder res) {
        res.append(text);
    }
}
