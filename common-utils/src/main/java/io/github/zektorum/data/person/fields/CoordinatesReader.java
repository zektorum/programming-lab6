package io.github.zektorum.data.person.fields;

import io.github.zektorum.data.person.PersonFieldsChecker;
import io.github.zektorum.io.InputChecker;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, содержаший методы, необходимые для чтения координат.
 */
public class CoordinatesReader implements InputChecker {
    /**
     * Создаёт объект координат на основе пользовательского ввода.
     *
     * @return объект координат
     */
    public Coordinates read() {
        System.out.println("Введите координаты");
        double x = this.readCoordinateX();
        Integer y = this.readCoordinateY();
        return new Coordinates(x, y);
    }

    /**
     * Создаёт объект координат на основе данных из пользовательского скрипта
     *
     * @param input scanner из интерпретатора, необходимый для считывания полей Person из файла
     * @return объект координат
     */
    public Coordinates readFromFile(Scanner input) {
        double x = this.readCoordinateXFromFile(input);
        Integer y = this.readCoordinateYFromFile(input);
        return new Coordinates(x, y);
    }

    /**
     * @return абсцисса координат
     */
    private double readCoordinateX() {
        String x_MAX = "1.7976931348623157*10^308";
        System.out.printf("Диапазон значений: x ∈ (-183, %s]\n", x_MAX);
        System.out.print("x: ");

        Scanner doubleScanner = new Scanner(System.in);
        checkInput(doubleScanner);
        double x = -200.;
        try {
            x = doubleScanner.nextDouble();
        } catch (InputMismatchException e) {
            checkInput(doubleScanner);
            doubleScanner.nextLine();
        }
        while (PersonFieldsChecker.isMoreThanValue(x, -183)) {
            System.out.println("Введённое значение не входит в диапазон!");
            System.out.printf("x ∈ (-183, %s]\n", x_MAX);
            System.out.print("Повторите попытку: ");
            try {
                checkInput(doubleScanner);
                x = doubleScanner.nextDouble();
            } catch (InputMismatchException e) {
                checkInput(doubleScanner);
                doubleScanner.nextLine();
            }
        }
        return x;
    }

    /**
     * @return ордината координат
     */
    private Integer readCoordinateY() {
        String y_MIN = "-2147483648";
        System.out.printf("Диапазон значений: y ∈ [%s, 750); y != null\n", y_MIN);
        System.out.print("y: ");

        Scanner intScanner = new Scanner(System.in);
        checkInput(intScanner);
        Integer y = 1000;
        try {
            y = intScanner.nextInt();
        } catch (InputMismatchException e) {
            checkInput(intScanner);
            intScanner.nextLine();
        }
        while (!PersonFieldsChecker.isLessThanValue(y, 750)) {
            System.out.println("Введённое значение не входит в диапазон!");
            System.out.printf("y ∈ [%s, 750)\n", y_MIN);
            System.out.print("Повторите попытку: ");
            try {
                checkInput(intScanner);
                y = intScanner.nextInt();
            } catch (InputMismatchException e) {
                checkInput(intScanner);
                intScanner.nextLine();
            }
        }
        return y;
    }

    /**
     * @param input scanner из интерпретатора, необходимый для считывания координаты
     * @return абсцисса координат
     */
    private double readCoordinateXFromFile(Scanner input) {
        checkInput(input);
        try {
            return input.nextDouble();
        } catch (InputMismatchException e) {
            return -183.;        }
    }

    /**
     * @param input scanner из интерпретатора, необходимый для считывания координаты
     * @return ордината координат
     */
    private Integer readCoordinateYFromFile(Scanner input) {
        checkInput(input);
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            return 750;
        }
    }
}
