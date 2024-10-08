import java.io.IOException;

public class ReverseMinCAbc {
    public static void main(String[] args) {


        int[] data = new int[10_000_000];
        int[] sizes = new int[10_000_000];

        int sizesLength = 0;
        int dataLength = 0;
        int countColum = 0;

        try (FastScanner in = new FastScanner(System.in)) {
            while (in.hasNextLine()) {
                // :NOTE: try с ресурсами
                try (FastScanner lineScanner = new FastScanner(in.nextLine())) {
                    int count = 0;
                    while (lineScanner.hasNextAbc()) {
                        data[dataLength++] = lineScanner.nextAbc();
                        count++;
                        countColum = Math.max(countColum, count);
                    }
                    sizes[sizesLength] = count;
                    sizesLength++;
                }
            }

            int offset = 0;
            Integer[] min = new Integer[countColum];
            for (int row = 0; row < sizesLength; row++) {
                for (int col = 0; col < sizes[row]; col++) {
                    if (min[col] == null || min[col] > data[offset]) {
                        min[col] = data[offset];
                    }
                    String c = toAbc(min[col]);
                    System.out.print(c + " ");
                    offset++;
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.print("Something wrong with input: " + e.getMessage());
        }
    }

    // :NOTE: name: toAbc
    // :NOTE: должен быть private
    private static String toAbc(int number) {
        String s = Integer.toString(number);
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            // :NOTE: заменить на условие '0' <= с <= '9'
            if (!('0' <= c && c <= '9')) {
                res.append(c);
            } else {
                c = (char) (c + 49);
                res.append(c);
            }
        }
        return res.toString();
    }
}
