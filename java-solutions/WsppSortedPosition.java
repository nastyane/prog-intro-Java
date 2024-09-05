import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppSortedPosition {
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
            for (String key : wordCount.keySet()) {
                List<Integer> newValues = new ArrayList<>();

                for (Integer value : wordCount.get(key)) {
                    newValues.add(position - value);
                }
                wordCount.put(key, newValues);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something wrong with input file: " + e.getMessage());
        }
        List<String> sortedWords = new ArrayList<>(wordCount.keySet());
        Collections.sort(sortedWords);
        LinkedHashMap<String, List<Integer>> sortMap = new LinkedHashMap<>();
        for (String sort : sortedWords) {
            sortMap.putIfAbsent(sort, wordCount.get(sort));
        }

        int strPosition = 1;
        String filenameWrite = args[1];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenameWrite, StandardCharsets.UTF_8))) {
            for (Map.Entry<String, List<Integer>> entry : sortMap.entrySet()) {
                String wordText = entry.getKey();
                List<Integer> positions = entry.getValue();
                StringBuilder positionsBuilder = new StringBuilder();
                for (int i = 0; i < positions.size(); i++) {
                    if (i > 0) {
                        positionsBuilder.append(" ");
                    }
                    positionsBuilder.append(positions.get(i));
                }
                writer.write(wordText + " " + sortMap.get(wordText).size());
                for (Integer position : positions) {
                    writer.write(" " + strPosition + ":" + position);
                }

                writer.newLine();
                strPosition++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something wrong with output file: " + e.getMessage());
        }
    }
}


