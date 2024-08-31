import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Wspp {
    public static void main(String[] args) {
        String filenameRead = args[0];
        LinkedHashMap<String, List<Integer>> wordCount = new LinkedHashMap<>();

        try (FastScanner scanner = new FastScanner(new BufferedReader(new FileReader(filenameRead, StandardCharsets.UTF_8)))) {
            int position = 1;
            while (scanner.hasNextWord()) {
                String wordStr = scanner.nextWord().toLowerCase();
                wordCount.putIfAbsent(wordStr, new ArrayList<>());
                wordCount.get(wordStr).add(position);
                position++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something wrong with input file: " + e.getMessage());
        }

        String filenameWrite = args[1];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenameWrite, StandardCharsets.UTF_8))) {
            for (Map.Entry<String, List<Integer>> entry : wordCount.entrySet()) {
                String wordText = entry.getKey();
                List<Integer> positions = entry.getValue();
                StringBuilder positionsBuilder = new StringBuilder();
                for (int i = 0; i < positions.size(); i++) {
                    if (i > 0) {
                        positionsBuilder.append(" ");
                    }
                    positionsBuilder.append(positions.get(i));
                }
                String positionsString = positionsBuilder.toString();
                writer.write(wordText + " " + wordCount.get(wordText).size() + " " + positionsString);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something wrong with output file: " + e.getMessage());
        }
    }
}


