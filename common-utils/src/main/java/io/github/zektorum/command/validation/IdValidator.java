package io.github.zektorum.command.validation;

import io.github.zektorum.command.CommandArgsArray;

public class IdValidator implements Validator {
    @Override
    public boolean validate(CommandArgsArray args) {
        try {
            Integer.parseInt(args.getArg(0));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
