package io.github.zektorum.data.person.fields;

import io.github.zektorum.core.Interpreter;
import io.github.zektorum.data.person.PersonFieldsChecker;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, осуществляющий чтение полей объекта Location с клавиатуры либо из файла.
 */
public class LocationReader {
    /**
     * Метод, создающий объект локации на основе пользовательского ввода.
     *
     * @return объект локации
     */
    public Location read() {
        System.out.println("Введите локацию");
        double x = this.readLocationX();
        Float y = this.readLocationY();
        Double z = this.readLocationZ();

        return new Location(x, y, z);
    }

    /**
     * Метод, создающий объект локации на основе данных из скрипта.
     *
     * @param input scanner из интерпретатора, запустившего скрипт
     * @return объект локации
     */
    public Location readFromFile(Scanner input) {
        double x = this.readLocationXFromFile(input);
        Float y = this.readLocationYFromFile(input);
        Double z = this.readLocationZFromFile(input);

        return new Location(x, y, z);
    }

    /**
     * @return абсцисса локации
     */
    public double readLocationX() {
        String x_MIN = "-1.7976931348623157*10^308";
        String x_MAX = "1.7976931348623157*10^308";
        System.out.printf("Диапазон значений: x ∈ [%s, %s]\n", x_MIN, x_MAX);
        System.out.print("x: ");

        Scanner doubleScanner = new Scanner(System.in);
        Interpreter.checkInput(doubleScanner);
        try {
            return doubleScanner.nextDouble();
        } catch (InputMismatchException e) {
            doubleScanner.nextLine();
        }
        while (true) {
            try {
                System.out.println("Введённое значение не входит в диапазон!");
                System.out.printf("x ∈ [%s, %s]\n", x_MIN, x_MAX);
                System.out.print("x: ");
                Interpreter.checkInput(doubleScanner);
                return doubleScanner.nextDouble();
            } catch (InputMismatchException e) {
                Interpreter.checkInput(doubleScanner);
                doubleScanner.nextLine();
            }
        }

    }

    /**
     * @return ордината локации
     */
    public Float readLocationY() {
        String y_MAX = "3.40282347*10^38";
        String y_MIN = "−3.40282347*10^38";
        System.out.printf("Диапазон значений: y ∈ [%s, %s]; y != null\n", y_MIN, y_MAX);
        System.out.print("y: ");

        Scanner floatScanner = new Scanner(System.in);
        Interpreter.checkInput(floatScanner);
        Float y = null;
        try {
            y = floatScanner.nextFloat();
        } catch (InputMismatchException e) {
            Interpreter.checkInput(floatScanner);
            floatScanner.nextLine();
        }
        while (!PersonFieldsChecker.isFieldNotNull(y)) {
            System.out.println("Введённое значение не входит в диапазон!");
            System.out.printf("y ∈ [%s, %s]\n", y_MIN, y_MAX);
            System.out.print("Повторите попытку: ");
            try {
                Interpreter.checkInput(floatScanner);
                y = floatScanner.nextFloat();
            } catch (InputMismatchException e) {
                Interpreter.checkInput(floatScanner);
                floatScanner.nextLine();
            }

        }
        return y;
    }

    /**
     * @return аппликата локации
     */
    public Double readLocationZ() {
        String z_MAX = "1.7976931348623157*10^308";
        String z_MIN = "−1.7976931348623157*10^308";
        System.out.printf("Диапазон значений: z ∈ [%s, %s]; z != null\n", z_MIN, z_MAX);
        System.out.print("z: ");

        Scanner doubleScanner = new Scanner(System.in);
        Interpreter.checkInput(doubleScanner);
        Double z = null;
        try {
            z = doubleScanner.nextDouble();
        } catch (InputMismatchException e) {
            Interpreter.checkInput(doubleScanner);
            doubleScanner.nextLine();
        }
        while (!PersonFieldsChecker.isFieldNotNull(z)) {
            System.out.println("Введённое значение не входит в диапазон!");
            System.out.printf("z ∈ [%s, %s]\n", z_MIN, z_MAX);
            System.out.print("Повторите попытку: ");
            try {
                Interpreter.checkInput(doubleScanner);
                z = doubleScanner.nextDouble();
            } catch (InputMismatchException e) {
                Interpreter.checkInput(doubleScanner);
                doubleScanner.nextLine();
            }
        }
        return z;
    }

    /**
     * @param input scanner из интерпретатора, запустившего скрипт
     * @return абсцисса локации
     */
    public double readLocationXFromFile(Scanner input) {
        Interpreter.checkInput(input);
        try {
            return input.nextDouble();
        } catch (InputMismatchException e) {
            return .0;
        }
    }

    /**
     * @param input scanner из интерпретатора, запустившего скрипт
     * @return ордината локации
     */
    public Float readLocationYFromFile(Scanner input) {
        Interpreter.checkInput(input);
        try {
            return input.nextFloat();
        } catch (InputMismatchException e) {
            return .0f;
        }
    }

    /**
     * @param input scanner из интерпретатора, запустившего скрипт
     * @return аппликата локации
     */
    public Double readLocationZFromFile(Scanner input) {
        Interpreter.checkInput(input);
        try {
            return input.nextDouble();
        } catch (InputMismatchException e) {
            return .0;
        }
    }
}