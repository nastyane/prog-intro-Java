package markup;

public class Strong {
    private final Text text;

    public Strong(Text example) {
        text = example;

    }

    public void toMarkdown(StringBuilder res) {
        res.append("__");
        text.toMarkdown(res);
        res.append("__");
    }

}
