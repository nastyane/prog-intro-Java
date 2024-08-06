import java.io.IOException;

public class Reverse {
    public static void main(String[] args)  {
        FastScanner in = new FastScanner(System.in);
        int[] data = new int[10_000_000];
        int[] sizes = new int[10_000_000];
        int sizesLength = 0;
        int dataLength = 0;
        try {
            while (in.hasNextLine()) {
                FastScanner lineScanner = new FastScanner(in.nextLine());
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
            for (int i = sizesLength - 1; i >= 0; i--) {
                for (int j = 0; j < sizes[i]; j++) {
                    System.out.print(data[offset--] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.printf("Something wrong with input: "+ e.getMessage());
        }

    }
}
