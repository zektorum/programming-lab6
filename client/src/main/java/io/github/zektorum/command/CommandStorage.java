package io.github.zektorum.command;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandStorage {
    private static final Map<String, Class<? extends BaseCommand>> commands = new HashMap<>();

    static {
        commands.put("help", HelpCommand.class);
        commands.put("execute_script", ExecuteScriptCommand.class);
        commands.put("show", ShowCommand.class);
        // commands.put("average_of_height", AverageOfHeightCommand.class);
    }

    public static Class<? extends BaseCommand> get(String command) {
        return commands.get(command);
    }

    public static boolean containsCommand(String commandName) {
        return commands.containsKey(commandName);
    }
}
