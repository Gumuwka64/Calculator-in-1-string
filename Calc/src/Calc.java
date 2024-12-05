import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/*Создай консольное приложение “Калькулятор”. Приложение должно читать из консоли введенные пользователем строки,
числа, арифметические операции проводимые между ними и выводить в консоль результат их выполнения.
Реализуй класс Main с методом public static String calc(String input). Метод должен принимать строку с арифметическим
выражением между двумя числами и возвращать строку с результатом их выполнения. Ты можешь добавлять свои импорты, классы
и методы. Добавленные классы не должны иметь модификаторы доступа (public или другие)
*/
public class Calc {
    public static void main(String[] args) {
        System.out.println("Введите арифметическое выражение.Например\"1+1\" ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scan.close();
        }
    }

    public static String calc(String input) {
        input = input.trim();
        String regex = "^(\\d{1,2}|[IVXivx]+)\\s*([+\\-*/])\\s*(\\d{1,2}|[IVXivx]+)$";
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("Не верный формат ввода.ожидается- a операция b");
        }
        String[] parts = input.split("\\s*([+\\-*/])\\s*");
        String firstNum = parts[0];
        String secondNum = parts[1];
        char operation = input.charAt(parts[0].length());


        int a = convertArab(firstNum);
        int b = convertArab(secondNum);

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно.");
        }
        switch (operation) {
            case '+':
                return String.valueOf(a + b);
            case '-':
                return String.valueOf(a - b);
            case '*':
                return String.valueOf(a * b);
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("На ноль делить нельзя");
                }
                return String.valueOf(a / b);
            default:
                throw new ArithmeticException("Не верная операция используйте только +,-,/,*");
        }
    }

    static int convertArab(String input) {
        if (input.matches("\\d+")) {
            return Integer.parseInt(input);
        } else {
            return romanArab(input);
        }
    }

    static int romanArab(String roman) {
        Map<Character, Integer> romaNum = new HashMap<>();
        romaNum.put('I', 1);
        romaNum.put('V', 5);
        romaNum.put('X', 10);
        romaNum.put('i', 1);
        romaNum.put('v', 5);
        romaNum.put('x', 10);

        int total = 0;
        int preNum = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            char cuChar = roman.charAt(i);
            int cuNum = romaNum.getOrDefault(cuChar, 0);

            if (cuNum < preNum) {
                total -= cuNum;
            } else {
                total += cuNum;
            }
            preNum = cuNum;
        }
        return total;
    }
}
