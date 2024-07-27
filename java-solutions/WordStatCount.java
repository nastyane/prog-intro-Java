import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class WordStatCount {
    public static void main(String[] args) {
        String filenameRead = args[0];

        LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filenameRead, StandardCharsets.UTF_8))) {
            StringBuilder word = new StringBuilder();
            int read;
            while ((read = reader.read()) != -1) {
                char ch = (char) read;

                if (checkSymbol(ch)) {
                    word.append(ch);
                    continue;
                }

                if (!word.isEmpty()) {
                    String wordStr = word.toString().toLowerCase();
                    wordCount.put(wordStr, wordCount.getOrDefault(wordStr, 0) + 1);
                    word.setLength(0);
                }
            }

            if (!word.isEmpty()) {
                String wordStr = word.toString().toLowerCase();
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

    private static boolean checkSymbol(char word) {
        return Character.isLetter(word) ||
               Character.getType(word) == Character.DASH_PUNCTUATION ||
               word == '\'';
    }
}
