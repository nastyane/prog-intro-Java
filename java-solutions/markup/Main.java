package markup;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Markup md = new Text("example");
        StringBuilder sb = new StringBuilder();
        md.toMarkdown(sb);
        System.out.println(sb);

        Markup md2 = new Emphasis(List.of(
                new Text("good"),
                new Text("bad"),
                new Emphasis(List.of(new Text("hello")))
        ));
        sb = new StringBuilder();

        md2.toMarkdown(sb);
        System.out.println(sb);

        Markup md3 = new Strong(List.of(
                new Text("good"),
                new Text("bad"),
                new Strong(List.of(new Text("hello")))
        ));
        md3.toMarkdown(sb);
        System.out.println(sb);

        Markup md4 = new Strikeout(List.of(
                new Text("good"),
                new Text("bad"),
                new Emphasis(List.of(new Text("hello")))
        ));
        md4.toMarkdown(sb);
        System.out.println(sb);
        Paragraph paragraph = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));

        StringBuilder strb = new StringBuilder();
        paragraph.toMarkdown(strb);
        strb.setLength(0);
        new Paragraph(List.of(
                new Paragraph(List.of(
                        new Text("text")
                ))
        )).toMarkdown(strb);
        System.out.println(strb.toString());
    }
}
