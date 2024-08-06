import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class WordStatCount {
    public static void main(String[] args) throws IOException {
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
            List<Map.Entry<String, Integer>> collect = wordCount.entrySet().stream().collect(Collectors.toList());
            Collections.sort(collect, Comparator.comparing(o -> o.getValue()));

            for (Map.Entry<String, Integer> entry : collect) {
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
