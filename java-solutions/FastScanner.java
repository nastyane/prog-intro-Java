import java.io.*;

public class FastScanner implements AutoCloseable {
    private final Reader reader;
    private int lastChar = -1;

    public FastScanner(Reader reader) {
        this.reader = reader;
    }

    public FastScanner(InputStream in) {
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

    public boolean hasNextLine() throws IOException {
        int symbol = readAndSkipR();
        if (symbol != -1) {
            lastChar = symbol;
            return true;
        } else {
            return false;
        }
    }

    public String nextLine() throws IOException {
        if (lastChar == '\n') {
            lastChar = -1;
            return "";
        }

        StringBuilder str = new StringBuilder();
        if (lastChar != -1) {
            char toChar = (char) lastChar;
            str.append(toChar);
        }

        while (true) {
            int symbolInt = readAndSkipR();
            char symbolChar = (char) symbolInt;
            if (symbolChar != '\n' && symbolInt != -1) {
                str.append(symbolChar);
            } else {
                lastChar = -1;
                break;
            }
        }
        return str.toString();
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

    public boolean hasNextInt() throws IOException {
        while (true) {
            int symbol = readAndSkipR();
            if (symbol == -1) {
                return false;
            } else if (Character.isWhitespace(symbol) || symbol == '+') {
                continue;
            } else if (!Character.isWhitespace(symbol) && Character.isDigit(symbol) || symbol == '-') {
                lastChar = symbol;
                return true;
            } else if (!Character.isDigit(symbol)) {
                return false;
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
}
