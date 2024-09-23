package markup;

public class Text implements Markup {
    private final String text;

    public Text(String example) {
        text = example;
    }

    @Override
    public void toMarkdown(StringBuilder res) {
        res.append(text);
    }
}
