public class SumDoubleSpace {
    public static void main(String[] args) {
        double result = 0;
        for (String arg : args) {
            result += parse(arg);
        }
        System.out.println(result);
    }

    public static double parse(String arg) {
        double sum = 0;
        // TODO :NOTE: удалить iml файлы
        // done
        // TODO :NOTE: избавиться от StringBuilder, использовать substring
        int startIndex = -1;
        for (int i = 0; i < arg.length(); i++) {
            char toChar = arg.charAt(i);
            if (!isWhitespace(toChar)) {
                if (startIndex == -1) {
                    startIndex = i;
                }
            } else {
                if (startIndex != -1) {
                    sum += Double.parseDouble(arg.substring(startIndex, i));
                    startIndex = -1;
                }
            }
        }

        if (startIndex != -1) {
            sum += Double.parseDouble(arg.substring(startIndex));
        }

        return sum;
    }

    private static boolean isWhitespace(char ch) {
        return Character.isWhitespace(ch) || ch == 160;
    }
}
