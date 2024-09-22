package markup;

public class Text {
    private final String text;

    public Text(String example) {
        text = example;
    }

    public void toMarkdown(StringBuilder res) {
        res.append(text);
    }

}
