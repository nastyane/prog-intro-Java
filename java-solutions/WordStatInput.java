import java.io.*;
import java.util.Arrays;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) throws Exception {
        ArrayList<String> words = new ArrayList<>();
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8")
        );
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0]), "UTF-8")
            );
            int[] count = new int[2];
            int first = 0;
            int second = 0;
            try {
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
                                words.add(str.substring(first, second).toLowerCase());
                                first = 0;
                            }
                        }
                    }
                }
                count = Arrays.copyOf(count, words.size());
                int temp = 0;
                for (int i = 0; i < words.size() - 1; i++) {
                    for (int j = i + 1; j < words.size(); j++) {
                        if ((words.get(i).equals(words.get(j)))) {
                            count[i]++;
                            temp = j-1;
                            words.remove(j);
                            j = temp;
                        }
                    }
                }
            } finally {
                System.out.println("Сlosed");
                in.close();
            }

            try {
                for (int j = 0; j < words.size(); j++) {
                    if (words.get(j) == null) {
                        continue;
                    } else {
                        out.write(words.get(j) + " " + (++count[j]));
                        out.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("File close");
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found" + e.getMessage());
        }
    }

    public static boolean cheakSymbol(char symbol) {
        return Character.isLetter(symbol) || Character.getType(symbol) == Character.DASH_PUNCTUATION || symbol == '\'';
    }

}

