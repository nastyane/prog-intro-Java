import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class WordStatCount {
    public static void main(String[] args) throws Exception {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8)
        )) {
            int first = 0;
            int second = 0;
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    break;
                }
                for (int i = 0; i < str.length(); i++) {
                    if (cheakSymbol(str.charAt(i))) {
                        if (i == 0 || !cheakSymbol(str.charAt(i - 1))) {
                            first = i;
                        }
                        if (i == str.length() - 1 || !cheakSymbol(str.charAt(i + 1))) {
                            second = ++i;

                            // :NOTE: файл целиком загружается в оперативную память, надо сразу считать их количество
                            words.add(str.substring(first, second).toLowerCase());
                            first = 0;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found read " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something id input file " + e.getMessage());
        } finally {
            System.out.println("Closed read ");
            in.close();
        }
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
        )) {
            for (int i = 0; i < words.size(); i++) {
                int wordCount = Collections.frequency(words, words.get(i));
                count.add(wordCount);
            }
            int temp = 0;
            for (int i = 0; i < words.size() - 1; i++) {
                for (int j = i + 1; j < words.size(); j++) {
                    if ((words.get(i).equals(words.get(j)))) {
                        count.get(i);
                        temp = j - 1;
                        words.remove(j);
                        j = temp;
                    }
                }
            }

            changePosition(words, count);
            for (int j = 0; j < words.size(); j++) {
                if (words.get(j) == null) {
                    continue;
                } else {
                    String word = words.get(j);
                    Integer i = count.get(++j);
                    out.write(word + " " + i);
                    System.err.println(word + " " + i);
                    out.newLine();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Not found write " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something id input file " + e.getMessage());
        } finally {
            System.out.println("Close write");
            out.close();
        }

    }


    private static void changePosition(ArrayList<String> words, ArrayList<Integer> count) {
        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = 0; j < words.size() - i - 1; j++) {
                if (count.get(j) > count.get(j + 1)) {
                    String tempWord = words.get(j);
                    int tempCount = count.get(j);
                    words.set(j, words.get(j + 1));
                    count.set(j, count.get(j + 1));
                    words.set(j++, tempWord);
                    count.set(j++, tempCount);
                }
            }
        }
    }

    private static void addWord(ArrayList<String> words, ArrayList<Integer> count) {
        int temp = 0;
        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if ((words.get(i).equals(words.get(j)))) {
                    count.get(i);
                    temp = j - 1;
                    words.remove(j);
                    j = temp;
                }
            }
        }
    }

    public static boolean cheakSymbol(char symbol) {
        return Character.isLetter(symbol) || Character.getType(symbol) == Character.DASH_PUNCTUATION || symbol == '\'';
    }
}
