package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;

import java.util.Scanner;

public class AverageOfHeightCommand extends BaseCommand {
    private static final long serialVersionUID = 1234123499413L;

    public AverageOfHeightCommand() {}

    public AverageOfHeightCommand(Scanner scanner) {
        super(scanner);
    }

    @Override
    public String getName() {
        return "average_of_height";
    }

    @Override
    public String getUsage() {
        return "average_of_height";
    }

    @Override
    public String getDescription() {
        return "вывести среднее значение поля height для всех элементов коллекции";
    }

    @Override
    public int getArgsCount() {
        return 0;
    }

    @Override
    public Person execute(Scanner scanner, CommandArgsArray args) {
        return null;
    }
}
