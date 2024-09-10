import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppSortedPosition {
    public static void main(String[] args) {
        String filenameRead = args[0];
        LinkedHashMap<String, List<Pair>> wordCount = new LinkedHashMap<>();
        int lineNumber = 1;
        try (FastScanner scanner = new FastScanner(new BufferedReader(new FileReader(filenameRead, StandardCharsets.UTF_8)))) {
            while (scanner.hasNextLine()) {
                List<String> words = new ArrayList<>();
                try (FastScanner lineScanner = new FastScanner(scanner.nextLine())) {
                    while (lineScanner.hasNextWord()) {
                        words.add(lineScanner.nextWord().toLowerCase());
                    }
                }

                int positionCount = words.size();
                for (int i = 0; i < positionCount; i++) {
                    String word = words.get(i);
                    wordCount.putIfAbsent(word, new ArrayList<>());
                    wordCount.get(word).add(new Pair(lineNumber, positionCount - i));
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
        LinkedHashMap<String, List<Pair>> sortMap = new LinkedHashMap<>();
        for (String sort : sortedWords) {
            sortMap.putIfAbsent(sort, wordCount.get(sort));
        }


        String filenameWrite = args[1];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenameWrite, StandardCharsets.UTF_8))) {
            for (Map.Entry<String, List<Pair>> entry : sortMap.entrySet()) {
                String wordText = entry.getKey();
                List<Pair> positions = entry.getValue();
                StringBuilder positionsBuilder = new StringBuilder();
                for (int i = 0; i < positions.size(); i++) {
                    if (i > 0) {
                        positionsBuilder.append(" ");
                    }
                    positionsBuilder.append(positions.get(i));
                }
                writer.write(wordText + " " + sortMap.get(wordText).size());
                for (Pair position : positions) {
                    writer.write(" " + position.lineNumber + ":" + position.positionInLine);
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
