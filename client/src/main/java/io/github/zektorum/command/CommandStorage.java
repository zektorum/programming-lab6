package io.github.zektorum.command;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandStorage {
    private static final Map<String, Class<? extends BaseCommand>> commands = new HashMap<>();

    static {
        commands.put("help", HelpCommand.class);
        commands.put("execute_script", ExecuteScriptCommand.class);
        commands.put("show", ShowCommand.class);
        commands.put("insert", InsertCommand.class);
        commands.put("update", UpdateCommand.class);
        commands.put("remove_key", RemoveKeyCommand.class);
        commands.put("remove_lower", RemoveLowerCommand.class);
        commands.put("average_of_height", AverageOfHeightCommand.class);
        commands.put("replace_if_lower", ReplaceIfLowerCommand.class);
        commands.put("print_field_ascending_location", PrintFieldAscendingLocationCommand.class);
        commands.put("count_greater_than_eye_color", CountGreaterThanEyeColorCommand.class);
        commands.put("remove_lower_key", RemoveLowerKeyCommand.class);
        commands.put("info", InfoCommand.class);
    }

    public static Class<? extends BaseCommand> get(String command) {
        return commands.get(command);
    }

    public static boolean containsCommand(String commandName) {
        return commands.containsKey(commandName);
    }
}
