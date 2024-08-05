import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;


public class WordStatInput {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String filenameRead = args[0];
        String filenameWrite = args[1];
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filenameRead), "UTF-8"));
            LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<>();
            StringBuilder word = new StringBuilder();
            int read;
            try {
                while ((read = reader.read()) != -1) {
                    char ch = (char) read;
                    if (!checkSymbol(ch)) {
                        if (!word.isEmpty()) {
                            String wordStr = word.toString().toLowerCase();
                            wordCount.put(wordStr, wordCount.getOrDefault(wordStr, 0) + 1);
                            word.setLength(0);
                        }
                    } else {
                        if (checkSymbol(ch)) {
                            word.append(ch);
                        }
                    }
                }
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(filenameWrite), "UTF-8"));
                    for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                        String wordText = entry.getKey();
                        int count = entry.getValue();

                        writer.write(wordText + " " + count);
                        writer.newLine();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File write not found: " + e.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    if (writer != null) {
                        writer.close();
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input" + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File read not found: " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static boolean checkSymbol(char word) {
        return Character.isLetter(word) ||
                Character.getType(word) == Character.DASH_PUNCTUATION ||
                word == '\'';
    }
}
