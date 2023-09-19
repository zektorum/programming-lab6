package io.github.zektorum.command.validation;

import io.github.zektorum.command.Command;
import io.github.zektorum.command.CommandArgsArray;

public class RemoveKeyCommandArgsValidator implements ValidatorGroup {
    private CommandArgsArray args;

    public RemoveKeyCommandArgsValidator(CommandArgsArray args) {
        this.args = args;
    }
    @Override
    public boolean validateGroup() {
        IdValidator validator = new IdValidator();
        return validator.validate(args);
    }
}
