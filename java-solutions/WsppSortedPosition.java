import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppSortedPosition {
    public static void main(String[] args) {
        // проверить корректность аргументов и если пользователь ошибся, вывести вменяему ошибку
        if (args == null || args.length != 2 || args[0] == null || args[0].isEmpty() || args[1] == null || args[1].isEmpty()) {
            System.err.println("Incorrect arguments. Usage: java <input_file> <output_file>");
            return;
        }
        String filenameRead = args[0];
        HashMap<String, List<CoordinationsWord>> wordsInLine = new HashMap<>(); // другое название переменной
        int lineNumber = 1;
        try (FastScanner scanner = new FastScanner(new BufferedReader(new FileReader(filenameRead, StandardCharsets.UTF_8)))) {
            while (scanner.hasNextLine()) {
                List<String> words = new ArrayList<>();
                try (FastScanner lineScanner = new FastScanner(scanner.nextLine())) {
                    while (lineScanner.hasNextWord()) {
                        words.add(lineScanner.nextWord().toLowerCase());
                    }
                }

                int countWords = words.size(); // другое название переменной
                for (int i = 0; i < countWords; i++) {
                    String word = words.get(i);

                    // использовать computeIfAbsent
                    List<CoordinationsWord> coordinationsWords = wordsInLine.computeIfAbsent(word, k -> new ArrayList<>());

                    coordinationsWords.add(new CoordinationsWord(lineNumber, countWords - i));
                }

                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Something wrong with input file: " + e.getMessage());
            return;
        }

        List<String> sortedWords = new ArrayList<>(wordsInLine.keySet());
        Collections.sort(sortedWords);
        LinkedHashMap<String, List<CoordinationsWord>> sortMap = new LinkedHashMap<>();
        for (String word : sortedWords) {
            sortMap.put(word, wordsInLine.get(word));
        }

        String filenameWrite = args[1];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenameWrite, StandardCharsets.UTF_8))) {
            for (Map.Entry<String, List<CoordinationsWord>> entry : sortMap.entrySet()) {
                String word = entry.getKey();
                List<CoordinationsWord> positions = entry.getValue();

                writer.write(word);
                writer.write(" ");
                writer.write(Integer.toString(sortMap.get(word).size()));

                for (CoordinationsWord position : positions) {
                    // избавиться от конкатенации строк
                    // lineNumer и positionInLine получать через геттеры
                    writer.write(" ");
                    writer.write(Integer.toString(position.getLineNumber()));
                    writer.write(":");
                    writer.write(Integer.toString(position.getPositionInLine()));
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
