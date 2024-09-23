package markup;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MarkupToMarkdown md = new Text("example");
        StringBuilder sb = new StringBuilder();
        md.toMarkdown(sb);
        System.out.println(sb);

        MarkupToMarkdown md2 = new Emphasis(new Text("good"));
        sb = new StringBuilder();

        md2.toMarkdown(sb);
        System.out.println(sb);

        MarkupToMarkdown md3 = new Emphasis(new Text("nice"));
        sb = new StringBuilder();
        md3.toMarkdown(sb);
        System.out.println(sb);

        Strikeout md4 = new Strikeout(new Text("wow"));
        sb = new StringBuilder();
        md4.toMarkdown(sb);
        System.out.println(sb);
//        Paragraph paragraph = new Paragraph(List.of(
//                new Strong(List.of(
//                        new Text("1"),
//                        new Strikeout(List.of(
//                                new Text("2"),
//                                new Emphasis(List.of(
//                                        new Text("3"),
//                                        new Text("4")
//                                )),
//                                new Text("5")
//                        )),
//                        new Text("6")
//                ))
//        ));
//        StringBuilder strb = new StringBuilder();
//        paragraph.toMarkdown(strb);
//        System.out.println(strb.toString());
    }
}
