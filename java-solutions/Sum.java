public class Sum {
    public static void main(String[] args) {
        int result = 0;
        for (String arg : args) {
            result += parse(arg);
        }
        System.out.println(result);
    }

    public static int parse(String arg) {
        int sum = 0;
        // :NOTE: починить -2147483648 (сначала переделать SumDoubleSpace)
        // :NOTE: Сделать аналогично SumDoubleSpace

        StringBuilder number = new StringBuilder();
        boolean minus = false;

        for (int i = 0; i < arg.length(); i++) {
            char toChar = arg.charAt(i);
            if (toChar == '-') {
                if (!number.isEmpty()) {
                    sum += Integer.parseInt(number.toString());
                    number.setLength(0);
                }
                minus = true;
            } else if (Character.isDigit(toChar)) {
                number.append(toChar);
            } else {
                if (!number.isEmpty()) {
                    if (minus) {
                        sum += Integer.parseInt(number.toString()) * -1;
                        minus = false;
                    } else {
                        sum += Integer.parseInt(number.toString());
                    }
                    number.setLength(0);
                }
            }
        }
        if (!number.isEmpty()) {
            if (minus) {
                sum += Integer.parseInt(number.toString()) * -1;
            } else {
                sum += Integer.parseInt(number.toString());
            }
        }
        return sum;
    }
}
