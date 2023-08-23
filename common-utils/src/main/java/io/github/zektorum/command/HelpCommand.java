package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;

import java.util.Map;

/**
 * Реализация команды help.
 */
public class HelpCommand extends BaseCommand {
    private static final long serialVersionUID = 1234123499453L;

    public HelpCommand() {}

   public String execute(CommandArgsArray args, Person person) {
       StringBuilder stringBuilder = new StringBuilder();
       try {
            for (Map.Entry<String, Class<? extends BaseCommand>> currentCommand : CommandMap.commands.entrySet()) {
                BaseCommand command = currentCommand.getValue().newInstance();
                stringBuilder.append(String.format(
                        "%s\n\t%s\n",
                        command.getUsage(),
                        command.getDescription()
                ));
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public int getArgsCount() {
        return 0;
    }

    @Override
    public boolean personInputRequired() {
        return false;
    }
}
