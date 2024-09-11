import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppSortedPosition {
    public static void main(String[] args) {
        // TODO :NOTE: проверить корректность аргументов и если пользователь ошибся, вывести вменяему ошибку
        String filenameRead = args[0];
        HashMap<String, List<Pair>> wordCount = new HashMap<>(); // TODO :NOTE: другое название переменной
        int lineNumber = 1;
        try (FastScanner scanner = new FastScanner(new BufferedReader(new FileReader(filenameRead, StandardCharsets.UTF_8)))) {
            while (scanner.hasNextLine()) {
                List<String> words = new ArrayList<>();
                try (FastScanner lineScanner = new FastScanner(scanner.nextLine())) {
                    while (lineScanner.hasNextWord()) {
                        words.add(lineScanner.nextWord().toLowerCase());
                    }
                }

                int positionCount = words.size(); // TODO :NOTE: другое название переменной
                for (int i = 0; i < positionCount; i++) {
                    String word = words.get(i);

                    List<Pair> pairs = wordCount.get(word);
                    // TODO :NOTE: использовать computeIfAbsent
                    if (pairs == null) {
                        pairs = new ArrayList<>();
                        wordCount.put(word, pairs);
                    }

                    pairs.add(new Pair(lineNumber, positionCount - i));
                }

                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            // TODO :NOTE: return
        } catch (IOException e) {
            System.out.println("Something wrong with input file: " + e.getMessage());
            // TODO :NOTE: return
        }

        List<String> sortedWords = new ArrayList<>(wordCount.keySet());
        Collections.sort(sortedWords);
        LinkedHashMap<String, List<Pair>> sortMap = new LinkedHashMap<>();
        for (String word : sortedWords) {
            sortMap.put(word, wordCount.get(word));
        }

        String filenameWrite = args[1];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenameWrite, StandardCharsets.UTF_8))) {
            for (Map.Entry<String, List<Pair>> entry : sortMap.entrySet()) {
                String word = entry.getKey();
                List<Pair> positions = entry.getValue();

                writer.write(word);
                writer.write(" ");
                writer.write(Integer.toString(sortMap.get(word).size()));

                for (Pair position : positions) {
                    // TODO :NOTE: избавиться от конкатенации строк
                    // TODO :NOTE: lineNumer и positionInLine получать через геттеры
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

// TODO :NOTE: переместить его как внутренний класс или как внешний
// TODO :NOTE: другое название класса
class Pair {
    int lineNumber;
    int positionInLine;

    public Pair(int lineNumber, int positionInLine) {
        this.lineNumber = lineNumber;
        this.positionInLine = positionInLine;
    }
}
