public class SumDoubleSpace {
    public static void main(String[] args) {
        double result = 0;
        // "       3     2.6    -3.934"
        // args = new String[]{"1"};
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
                if (startIndex != -1){
                    String number = arg.substring(startIndex, i);
                    sum += Double.parseDouble(number);
                    startIndex= -1;
                }

            }
        }
        if (startIndex != -1) {
            String numberS = arg.substring(startIndex);
            sum += Double.parseDouble(numberS);
        }

        return sum;
    }

    private static boolean isWhitespace(char ch) {
        return Character.isWhitespace(ch) || ch == 160;
    }
}
