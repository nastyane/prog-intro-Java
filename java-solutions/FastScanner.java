import java.io.*;

public class FastScanner {
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

    public boolean hasNextLine() throws IOException {
        int symbol = reader.read();
        if (symbol != -1) {
            lastChar = symbol;
            return true;
        } else {
            return false;
        }
    }

    public String nextLine() throws IOException {
        StringBuilder str = new StringBuilder();
        if (lastChar != -1 && lastChar != '\n') {
            char toChar = (char) lastChar;
            str.append(toChar);
        }
        if (lastChar == '\n') {
            lastChar = -1;
            return str.toString();
        }
        while (true) {
            int symbolInt = reader.read();
            char symbolChar = (char) symbolInt;
            if (symbolChar != '\n' && symbolChar != '\r' && symbolInt != -1) {
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
            char symbol = (char) reader.read();
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
/*        if (symbol != -1 && !Character.isWhitespace(symbol)) {
            lastChar = symbol;
            return true;
        }*/
        while (true) {
            int symbol = reader.read();
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
            int number = reader.read();
            if (!Character.isWhitespace(number) && number != -1) {
                str.append((char) number);
            } else {

                break;
            }
        }

        return Integer.parseInt(str.toString());
    }
}
