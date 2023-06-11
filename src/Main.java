import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws ScannerExeption {
        System.out.println("Введите выражение через пробел");
        String str = s.nextLine(); //вводим выражение с клавиатуры
        System.out.println(calc(str));
    }

    public static String calc(String input) throws ScannerExeption {
        input = input.trim();  // убираем пробелы в начале и в конце вводимой строки
        input = input.toUpperCase(); //переводим все символы в верхний регистр, если вдруг пользователь написал римские цифры нижним регистром
        String[] myArray = input.split(" "); //используем пробел как разделитель и помещаем элементы, разделенные им, в массив
        if (myArray.length != 3) {
            throw new ScannerExeption("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        }

        String numeral1 = myArray[0]; //первое число
        String sign = myArray[1]; // оператор
        String numeral2 = myArray[2]; //второе число


        // Проверяем, являются ли введенные числа арабскими или римскими
        boolean num1Ar = isNumber(numeral1);
        boolean num2Ar = isNumber(numeral2);
        boolean num1Rom = isRomanNumber(numeral1);
        boolean num2Rom = isRomanNumber(numeral2);

        //Проверка на одновременное использование арабского и римского числа
        if ((num1Ar && num2Rom) || (num2Ar && num1Rom)) {
            throw new ScannerExeption("throws Exception //т.к. используются одновременно разные системы счисления");
        }
        //Два арабских числа
        else if (num1Ar && num2Ar) {
            int a = Integer.parseInt(numeral1);
            int b = Integer.parseInt(numeral2);
            //исключаем числа больше 10
            if ((a > 10) || (b > 10)) {
                throw new ScannerExeption("throws Exception //т.к. введены числа больше 10");
            }
            //исключаем отрицательные числа
            if ((a <= 0) || (b <= 0)) {
                throw new ScannerExeption("throws Exception //т.к. введены отрицательные числа или ноль");
            }
            switch (sign) {
                case "+" -> input = String.valueOf(a + b);
                case "-" -> input = String.valueOf(a - b);
                case "*" -> input = String.valueOf(a * b);
                case "/" -> input = String.valueOf(a / b);
                default -> throw new ScannerExeption("throws Exception //т.к. введен неизвестный оператор");
            }
        }
        //Два римских числа
        else if (num1Rom && num2Rom) {
            RomanNumeral romanNumeral1 = RomanNumeral.valueOf(numeral1);
            RomanNumeral romanNumeral2 = RomanNumeral.valueOf(numeral2);

            int a = romanNumeral1.getArabicNumeral();
            int b = romanNumeral2.getArabicNumeral();
            int c;
            int d;
            if (a > 10 || b > 10) {
                throw new ScannerExeption("throws Exception //т.к. введены числа больше 10");
            }

            switch (sign) {
                case "+" -> {
                    c = a + b;
                    d = c % 10;  //Остаток от деления
                    c = c / 10;  //Получаем число десятков
                    //из двух enum по индексу получаем римские десятки и единицы, после чего складываем их
                    //если число единиц равно нулю, то используем только десятки, если число десятков равно нулю, то только единицы
                    //                 RomanNumeralDozen romanC = RomanNumeralDozen.values()[c - 1];
                    if (c == 0) {
                        RomanNumeral romanD = RomanNumeral.values()[d - 1];
                        input = String.valueOf(romanD);
                    } else if (d == 0) {
                        RomanNumeralDozen romanC = RomanNumeralDozen.values()[c - 1];
                        input = String.valueOf(romanC);
                    } else {
                        RomanNumeralDozen romanC = RomanNumeralDozen.values()[c - 1];
                        RomanNumeral romanD = RomanNumeral.values()[d - 1];
                        input = String.valueOf(romanC) + romanD;
                    }
                }
                case "-" -> {
                    if (a < b) {
                        throw new ScannerExeption("throws Exception //т.к. в римской системе нет отрицательных чисел и нуля");
                    } else {
                        c = a - b;
                        RomanNumeral value = RomanNumeral.values()[c - 1];
                        input = String.valueOf(value);
                    }
                }
                case "*" -> {
                    c = a * b;
                    d = c % 10;  //Остаток от деления
                    c = c / 10;  //Получаем число десятков
                    //из двух enum по индексу получаем римские десятки и единицы, после чего складываем их
                    //если число единиц равно нулю, то используем только десятки, если число десятков равно нулю, то только единицы
                    //                 RomanNumeralDozen romanC = RomanNumeralDozen.values()[c - 1];
                    if (c == 0) {
                        RomanNumeral romanD = RomanNumeral.values()[d - 1];
                        input = String.valueOf(romanD);
                    } else if (d == 0) {
                        RomanNumeralDozen romanC = RomanNumeralDozen.values()[c - 1];
                        input = String.valueOf(romanC);
                    } else {
                        RomanNumeralDozen romanC = RomanNumeralDozen.values()[c - 1];
                        RomanNumeral romanD = RomanNumeral.values()[d - 1];
                        input = String.valueOf(romanC) + romanD;
                    }
                }
                case "/" -> {
                    c = a / b;
                    RomanNumeral value = RomanNumeral.values()[c - 1];
                    input = String.valueOf(value);
                }
                default -> throw new ScannerExeption("throws Exception //т.к. введены неизвестный оператор");
            }

        } else {
            throw new ScannerExeption("throws Exception //т.к. введены некорректные значения");
        }


        return input;
    }

    public static boolean isNumber(String str) {  //Метод проверяет, является ли строка числом и возвращает true, если является
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isRomanNumber(String str) {  //Метод проверяет, является ли строка РИМСКИМ числом и возвращает true, если является
        try {
            RomanNumeral.valueOf(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
