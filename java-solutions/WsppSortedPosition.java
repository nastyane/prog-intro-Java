import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppSortedPosition {
    public static void main(String[] args) {
        String filenameRead = "input.txt";
        LinkedHashMap<String, List<Pair>> wordCount = new LinkedHashMap<>();
        int lineNumber = 1;
        try (FastScanner scanner = new FastScanner(new BufferedReader(new FileReader(filenameRead, StandardCharsets.UTF_8)))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");
                int position = 1;
                String wordStr = scanner.nextWord().toLowerCase();
                wordCount.putIfAbsent(wordStr, new ArrayList<>());
                wordCount.get(wordStr).add(new Pair(lineNumber, position));
                position++;
                for (String key : wordCount.keySet()) {
                    List<Integer> newValues = new ArrayList<>();

                    for (Integer value : wordCount.get(key)) {
                        newValues.add(position - value);
                    }
                    wordCount.put(key, newValues);
                }
                lineNumber++;
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


        String filenameWrite = "output.txt";

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
                    writer.write(":" + position);
                }

                writer.newLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something wrong with output file: " + e.getMessage());
        }
    }


}

class Pair {
    int lineNumber;
    int positionInLine;

    public Pair(int lineNumber, int positionInLine) {
        this.lineNumber = lineNumber;
        this.positionInLine = positionInLine;
    }
}
