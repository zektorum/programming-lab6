package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

/**
 * Реализация команды average_of_height.
 */
public class AverageOfHeightCommand extends BaseCommand {
    private static final long serialVersionUID = 1234123499413L;

    public AverageOfHeightCommand() {}

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
    public boolean personInputRequired() {
        return false;
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();
        DoubleSummaryStatistics stats = storage.stream()
                .collect(Collectors.summarizingDouble(Person::getHeight));
        return String.format("%.2f\n", stats.getAverage());
    }
}
