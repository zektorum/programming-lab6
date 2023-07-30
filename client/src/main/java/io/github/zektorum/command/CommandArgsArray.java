package io.github.zektorum.command;

import java.io.Serializable;
import java.util.List;

public class CommandArgsArray implements Serializable {
    private List<String> args;

    public CommandArgsArray(int argsCount, List<String> args) throws WrongArgumentsException {
        if (argsCount != args.size()) {
            throw new WrongArgumentsException("Неверное число аргументов");
        }
        this.args = args;
    }

    public String getArg(int index) {
        return this.args.get(index);
    }

    public int getCount() {
        return args.size();
    }
}
