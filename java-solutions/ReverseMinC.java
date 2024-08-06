import java.io.IOException;
import java.util.Scanner;

public class ReverseMinC {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        int[] data = new int[1_000_000];
        int[] sizes = new int[1_000_000];

        int sizesLength = 0;
        int dataLength = 0;
        int countColum = 0;

        try {
            while (in.hasNextLine()) {
                FastScanner lineScanner = new FastScanner(in.nextLine());
                int count = 0;

                while (lineScanner.hasNextInt()) {
                    data[dataLength++] = lineScanner.nextInt();
                    count++;
                    countColum = Math.max(countColum, count);
                }

                sizes[sizesLength] = count;
                sizesLength++;
            }

            int offset = 0;
            Integer[] min = new Integer[countColum];
            for (int row = 0; row < sizesLength; row++) {
                for (int col = 0; col < sizes[row]; col++) {
                    if (min[col] == null || min[col] > data[offset]) {
                        min[col] = data[offset];
                    }

                    System.out.print(min[col] + " ");
                    offset++;
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.print("Something wrong with input: " + e.getMessage());
        }

    }
}

/*
1
1      2
3
1     2     3
4     5
6
1       2       3

4       5
6
1       2       3
-4       -5
6

1
1 2
1
1 2 3
1 2
1
1 2 3

1 2
1
1 2 3
-4 -5
-4
 */
