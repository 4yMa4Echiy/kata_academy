import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Main {
    private static String calc(String input) throws Exception {

        enum RomanDigit // Соответствие римских цифр арабским
        {
            I(1), II(2), III(3), IV(4),
            V(5), VI(6), VII(7), VIII(8),
            IX(9), X(10);

            private int value;

            RomanDigit(int newValue) {
                value=newValue;
            }

            public int getValue(){
                return value;
            }

            public static RomanDigit getByValue(int value) {
                for (RomanDigit rn : RomanDigit.values()) {
                    if (rn.getValue() == value)
                        return rn;
                }
                throw new IllegalArgumentException("Нет такого значения " + value);
            }

        }


        String parts[] = input.replaceAll("\\s+", "").split("[\\/\\+\\-\\*]");
        if (parts.length < 2) {
            throw new Exception("//т.к. строка не является математической операцией");
        } else if (parts.length > 2) {
            throw new Exception("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (input.split("[0-9]").length > 1 && input.split("[IVX]").length > 1) {
            throw new Exception("//т.к. используются одновременно разные системы счисления");
        } else if (input.split("[IVX]").length > 1)
            if ((RomanDigit.valueOf(parts[0]).value < RomanDigit.valueOf(parts[1]).value) && input.contains("-")){
                throw new Exception("//т.к. в римской системе нет отрицательных чисел");
        }


        Integer countRomanDigit = input.length() - input.replaceAll("[IVX]", "").length();
        Integer countArabDigit = input.length() - input.replaceAll("[0-9]", "").length();

        Integer a;
        Integer b;
        String result = null;

        if (countRomanDigit > 0) { // Если римские цифры
            a = RomanDigit.valueOf(parts[0]).value;
            b = RomanDigit.valueOf(parts[1]).value;

            if (input.contains("+")){
                result = String.valueOf(RomanDigit.getByValue(a + b));
            } else if (input.contains("-")) {
                result = String.valueOf(RomanDigit.getByValue(a - b));
            } else if (input.contains("/")) {
                result = String.valueOf(RomanDigit.getByValue(a / b));
            } else if (input.contains("*")) {
                result = String.valueOf(RomanDigit.getByValue(a * b));
            }

        } else if (countArabDigit > 0) {
            a = parseInt(parts[0]);
            b = parseInt(parts[1]);

            if (input.contains("+")) {
                result = Integer.toString(a + b);
            } else if (input.contains("-")) {
                result = Integer.toString(a - b);
            } else if (input.contains("/")) {
                result = Integer.toString(a / b);
            } else if (input.contains("*")) {
                result = Integer.toString(a * b);
            }

        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
}

