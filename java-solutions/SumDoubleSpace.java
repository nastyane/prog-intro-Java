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
        // TODO :NOTE: избавиться от StringBuilder, использовать substring
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < arg.length(); i++) {
            char toChar = arg.charAt(i);
            if (!isWhitespace(toChar)) {
                number.append(toChar);
            } else if (!number.isEmpty()) {
                sum += Double.parseDouble(number.toString());
                number.setLength(0);
            }
        }

        if (!number.isEmpty()) {
            sum += Double.parseDouble(number.toString());
        }

        return sum;
    }

    private static boolean isWhitespace(char ch) {
        return Character.isWhitespace(ch) || ch == 160;
    }
}
