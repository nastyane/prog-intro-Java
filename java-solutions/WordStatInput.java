import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;


public class WordStatInput {
    public static void main(String[] args) {
        String filenameRead = args[0];

        LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<>();
        try (FastScanner scanner = new FastScanner(new BufferedReader(new FileReader(filenameRead, StandardCharsets.UTF_8)))) {
            while (scanner.hasNextWord()) {
                String wordStr = scanner.nextWord().toLowerCase();
                wordCount.put(wordStr, wordCount.getOrDefault(wordStr, 0) + 1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something wrong with input file: " + e.getMessage());
        }

        String filenameWrite = args[1];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenameWrite, StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                String wordText = entry.getKey();
                int count = entry.getValue();

                writer.write(wordText + " " + count);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something wrong with output file: " + e.getMessage());
        }
    }
}
