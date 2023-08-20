package io.github.zektorum.command;

import java.util.*;

public class CommandMap {
    public static final Map<String, Class<? extends BaseCommand>> commands = new HashMap<>();

    static {
        commands.put("help", HelpCommand.class);
        commands.put("insert", InsertCommand.class);
        commands.put("show", ShowCommand.class);
        commands.put("update", UpdateCommand.class);
    }
}
