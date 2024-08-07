import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.function.Predicate;

public class FastScanner implements AutoCloseable {
    private final Reader reader;
    private int lastChar = -1;

    public FastScanner(Reader reader) {
        this.reader = reader;
    }

    public FastScanner(InputStream in) {
        // TODO :NOTE: не надо оборачивать в BufferedReader
        this(new BufferedReader(new InputStreamReader(in)));
    }

    public FastScanner(String s) {
        this(new StringReader(s));
    }

    private int readAndSkipR() throws IOException {
        while (true) {
            int read = reader.read();
            if (read == '\r') {
                continue;
            }
            return read;
        }
    }

    private boolean hasNext(Predicate<Character> predicate) throws IOException {
        while (true) {
            int symbol = readAndSkipR();
            if (symbol == -1) {
                return false;
            } else if (predicate.test((char) symbol)) {
                lastChar = symbol;
                return true;
            }
        }
    }

    private String next(Predicate<Character> predicate) throws IOException {
        // TODO :NOTE: StringBuilder унести в поле класса и переиспользовать его
        // TODO :NOTE: str.setLength(0); сбрасывает внутренние указатели стринг билдера (переиспользование памяти)
        StringBuilder str = new StringBuilder();
        if (lastChar != -1) {
            str.append((char) lastChar);
            lastChar = -1;
        }
        while (true) {
            int number = readAndSkipR();
            if (number == -1) {
                break;
            }
            if (predicate.test((char) number)) {
                str.append((char) number);
            } else {
                break;
            }
        }
        return str.toString();
    }

    public boolean hasNextLine() throws IOException {
        return hasNext(ch -> true);
    }

    public String nextLine() throws IOException {
        if (lastChar == '\n') {
            lastChar = -1;
            return "";
        }

        return next(ch -> ch != '\n');
    }

    public String next() throws IOException {
        String s = null;

        if (lastChar != -1) {
            char toChar = (char) lastChar;
            s = String.valueOf(toChar);
        }
        while (true) {
            char symbol = (char) readAndSkipR();
            if (!Character.isWhitespace(symbol)) {
                lastChar = symbol;
                s = s + symbol;
            } else {
                break;
            }
        }
        return s;
    }

    // TODO :NOTE: Обобщить все оставшиеся метода через hasNext(Predicate) и next(Predicate), если синтаксис лямбда функций пока не понятин, сделать как в abc, завести обычную функцию и её передать
    public boolean hasNextInt() throws IOException {
        while (true) {
            int symbol = readAndSkipR();
            if (symbol == -1) {
                return false;
            } else if (Character.isDigit(symbol) || symbol == '-' || symbol == '+') {
                lastChar = symbol;
                return true;
            }
        }
    }

    public int nextInt() throws IOException {
        StringBuilder str = new StringBuilder();
        if (lastChar != -1) {
            str.append((char) lastChar);
            lastChar = -1;
        }
        while (true) {
            int number = readAndSkipR();
            if (!Character.isWhitespace(number) && number != -1) {
                str.append((char) number);
            } else {
                break;
            }
        }
        return Integer.parseInt(str.toString());
    }

    public boolean hasNextWord() throws IOException {
        while (true) {
            int symbol = readAndSkipR();
            if (symbol == -1) {
                return false;
            }

            if (checkSymbol((char) symbol)) {
                lastChar = symbol;
                return true;
            }
        }
    }

    public String nextWord() throws IOException {
        StringBuilder str = new StringBuilder();
        if (lastChar != -1) {
            str.append((char) lastChar);
            lastChar = -1;
        }
        while (true) {
            int number = readAndSkipR();
            if (number == -1) {
                return str.toString();
            }

            if (checkSymbol((char) number)) {
                str.append((char) number);
            } else {
                break;
            }
        }
        return str.toString();
    }

    private static boolean checkSymbol(char word) {
        return Character.isLetter(word) ||
               Character.getType(word) == Character.DASH_PUNCTUATION ||
               word == '\'';
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public boolean hasNextAbc() throws IOException {
        return hasNext(FastScanner::checkSymbolAbc);
    }

    public int nextAbc() throws IOException {
        return toNumber(next(FastScanner::checkSymbolAbc));
    }

    private static boolean checkSymbolAbc(char symbol) {
        // TODO :NOTE: Заменить isLetter на 'a' <= c <= 'какой-то там символ мне лень' т.к. известен изначальный алфавит и это будет работать быстрее
        return Character.isLetter(symbol) || symbol == '+' || symbol == '-';
    }

    public static int toNumber(String letter) {
        StringBuilder res = new StringBuilder();
        for (char c : letter.toCharArray()) {
            // TODO :NOTE: Заменить isLetter на 'a' <= c <= 'какой-то там символ мне лень'
            if (!Character.isLetter(c)) {
                res.append(c);
            } else {
                c = (char) (c - 49);
                res.append(c);
            }
        }
        return Integer.parseInt(res.toString());
    }
}
