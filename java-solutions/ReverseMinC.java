import java.util.Scanner;

public class ReverseMinC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer[] data = new Integer[1_000_000];
        int[] sizes = new int[1_000_000];
        int sizesLength = 0;
        int dataLength = 0;
        int countColum = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            int count = 0;

            while (lineScanner.hasNextInt()) {
                data[dataLength] = lineScanner.nextInt();
                dataLength++;
                count++;
                if (countColum < count) {
                    countColum = count;
                }
            }
            sizes[sizesLength] = count;
            sizesLength++;
        }
        int offset = 0;
        Integer[] min = new Integer[countColum];
        for (int row = 0; row < sizesLength; row++) {
            for (int col = 0; col < sizes[row]; col++) {
                if (min[col] == null) {
                    min[col] = data[offset];
                }
                if (min[col] > data[offset]) {
                    min[col] = data[offset];
                } else {
                    data[offset] = min[col];
                }

                System.out.print(data[offset++] + " ");
            }
            System.out.println();
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
