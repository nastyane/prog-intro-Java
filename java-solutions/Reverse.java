import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] data = new int[1_000_000];
        int[] sizes = new int[1_000_000];
        int sizesLength = 0;
        int dataLength = 0;
        int length = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            int count = 0;

            while (lineScanner.hasNextInt()) {
                data[dataLength] = lineScanner.nextInt();
                dataLength++;
                count++;
            }
            sizes[sizesLength] = count;
            sizesLength++;
        }

        int offset = dataLength - 1;
        for (int i = sizesLength-1; i >= 0; i--) {
            for (int j = 0; j < sizes[i]; j++) {
                System.out.print(data[offset--] + " ");
            }
            System.out.println();
        }

    }
}
