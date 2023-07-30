package io.github.zektorum.command;

import java.util.*;

public class CommandMap {
    public static final Map<String, Class<? extends BaseCommand>> commands = new HashMap<>();

    static {
        commands.put("help", HelpCommand.class);
    }
}
