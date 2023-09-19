package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;

import java.util.Comparator;
import java.util.stream.Collectors;

public class PrintFieldAscendingLocationCommand extends BaseCommand {
    @Override
    public String getName() {
        return "print_field_ascending_location";
    }

    @Override
    public String getUsage() {
        return "print_field_ascending_location";
    }

    @Override
    public String getDescription() {
        return "вывести значения поля location всех элементов в порядке возрастания";
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
    public boolean validate(CommandArgsArray args) {
        return true;
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();
        return storage.stream()
                .sorted(Comparator.comparing(Person::getLocation))
                .map(x -> x.getLocation().toString())
                .collect(Collectors.joining("\n")) + "\n";
    }
}
