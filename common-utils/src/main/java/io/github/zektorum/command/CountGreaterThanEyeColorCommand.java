package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;
import io.github.zektorum.data.person.fields.Color;

public class CountGreaterThanEyeColorCommand extends BaseCommand {
    @Override
    public String getName() {
        return "count_greater_than_eye_color";
    }

    @Override
    public String getUsage() {
        return "count_greater_than_eye_color eyeColor";
    }

    @Override
    public String getDescription() {
        return "вывести количество элементов, значение поля eyeColor которых больше заданного";
    }

    @Override
    public int getArgsCount() {
        return 1;
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
        Color.EyeColor inputColor = Color.EyeColor.valueOf(args.getArg(0).toUpperCase());
        long counter = storage.stream()
                .filter(x -> x.getEyeColor().getValue() > inputColor.getValue())
                .count();
        return String.format("%d\n", counter);
    }
}
