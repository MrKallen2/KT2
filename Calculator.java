import java.util.Locale;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("Выберите операцию:");
        System.out.println("1. +");
        System.out.println("2. -");
        System.out.println("3. *");
        System.out.println("4. /");

        int choice;
        while (true) {
            System.out.print("Ваш выбор (1-4): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {
                    break;
                } else {
                    System.out.println("Пожалуйста, введите число от 1 до 4.");
                }
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
                scanner.next(); // очистка буфера
            }
        }

        double num1, num2;
        while (true) {
            System.out.print("Введите число 1: ");
            if (scanner.hasNextDouble()) {
                num1 = scanner.nextDouble();
                break;
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите дробное число (разделитель — точка).");
                scanner.next(); // очистка буфера
            }
        }

        while (true) {
            System.out.print("Введите число 2: ");
            if (scanner.hasNextDouble()) {
                num2 = scanner.nextDouble();
                break;
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите дробное число (разделитель — точка).");
                scanner.next(); // очистка буфера
            }
        }

        double result = 0;
        switch (choice) {
            case 1:
                result = num1 + num2;
                break;
            case 2:
                result = num1 - num2;
                break;
            case 3:
                result = num1 * num2;
                break;
            case 4:
                if (num2 == 0) {
                    System.out.println("Ошибка: деление на ноль невозможно.");
                    return;
                }
                result = num1 / num2;
                break;
        }

        System.out.println(result);
        scanner.close();
    }
}
