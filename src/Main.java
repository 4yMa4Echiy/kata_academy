import java.util.Scanner;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main {
    private static String calc(String input) throws Exception {

        class RomanDigit {
            public String getByInt(int number) {
                String[] thousands = {"", "M", "MM", "MMM"};
                String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
                String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
                String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
                return thousands[number / 1000] + hundreds[(number % 1000) / 100] + tens[(number % 100) / 10] + units[number % 10];
            }

            public Integer getByRoman(String number) {
                Map<String, Integer> dictRoman = new HashMap<String, Integer>();
                String[][] mergedRoman = {{"", "M", "MM", "MMM"}, //Thousands
                        {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, //Hundreds
                        {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, //Tens
                        {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}}; //Units

                int z = 1000;
                for (int x = 0; x < mergedRoman.length; x++) {
                    for (int y = 0; y < mergedRoman[x].length; y++) {
                        dictRoman.put(mergedRoman[x][y], y * z);
                    }
                    z = z / 10;
                }

                boolean run = true;
                int v = 1, result = 0, curPos = 0;
                while (run) {
                    //System.out.println(number.substring(curPos, v) + " = " + dictRoman.get(number.substring(curPos, v)));
                    if (dictRoman.get(number.substring(curPos, v)) == null) {
                        v--;
                        result = result + dictRoman.get(number.substring(curPos, v));
                        curPos = v;
                    }
                    if (v == number.length()) {
                        result = result + dictRoman.get(number.substring(curPos, v));
                        run = false;
                    }
                    v++;
                }
                return result;
            }
        }
        RomanDigit romanDigit = new RomanDigit();


        String parts[] = input.replaceAll("\\s+", "").split("[\\/\\+\\-\\*]");


        if (parts.length < 2) {
            throw new Exception("//т.к. строка не является математической операцией");
        } else if (parts.length > 2) {
            throw new Exception("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (input.matches("(.*)[0-9](.*)") && input.matches("(.*)[XIV](.*)")) {
            throw new Exception("//т.к. используются одновременно разные системы счисления");
        } else if (input.split("[IVX]").length > 1){
            if ((romanDigit.getByRoman(parts[0]) < romanDigit.getByRoman(parts[1])) && input.contains("-")) {
                throw new Exception("//т.к. в римской системе нет отрицательных чисел");
            }
        }



        Integer countRomanDigit = input.length() - input.replaceAll("[IVX]", "").length();
        Integer countArabDigit = input.length() - input.replaceAll("[0-9]", "").length();

        Integer a;
        Integer b;
        Integer c = 0;

        if (countRomanDigit > 0) { // Если римские цифры

            a = romanDigit.getByRoman(parts[0]);
            b = romanDigit.getByRoman(parts[1]);

            if (input.contains("+")) {
                c = a + b;
            } else if (input.contains("-")) {
                c = a - b;
            } else if (input.contains("/")) {
                c = a / b;
            } else if (input.contains("*")) {
                c = a * b;
            }

            return romanDigit.getByInt(c);


        } else if (countArabDigit > 0) {

            a = parseInt(parts[0]);
            b = parseInt(parts[1]);

            if (input.contains("+")) {
                c = a + b;
            } else if (input.contains("-")) {
                c = a - b;
            } else if (input.contains("/")) {
                c = a / b;
            } else if (input.contains("*")) {
                c = a * b;
            }

        }
        return Integer.toString(c);
    }



    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
}


