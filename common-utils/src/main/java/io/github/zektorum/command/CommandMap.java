package io.github.zektorum.command;

import java.util.*;

public class CommandMap {
    public static final Map<String, Class<? extends BaseCommand>> commands = new HashMap<>();

    static {
        commands.put("help", HelpCommand.class);
        commands.put("insert", InsertCommand.class);
        commands.put("show", ShowCommand.class);
        commands.put("update", UpdateCommand.class);
        commands.put("remove_key", RemoveKeyCommand.class);
        commands.put("average_of_height", AverageOfHeightCommand.class);
        commands.put("replace_if_lower", ReplaceIfLowerCommand.class);
        commands.put("print_field_ascending_location", PrintFieldAscendingLocationCommand.class);
        commands.put("count_greater_than_eye_color", CountGreaterThanEyeColorCommand.class);
        commands.put("remove_lower_key", RemoveLowerKeyCommand.class);
        commands.put("info", InfoCommand.class);
    }
}
