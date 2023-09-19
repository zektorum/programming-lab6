package io.github.zektorum.command.validation;

import io.github.zektorum.command.CommandArgsArray;

public class ReplaceIfLowerCommandArgsValidator implements ValidatorGroup {
    private CommandArgsArray args;

    public ReplaceIfLowerCommandArgsValidator(CommandArgsArray args) {
        this.args = args;
    }

    @Override
    public boolean validateGroup() {
        IdValidator validator = new IdValidator();
        return validator.validate(args);
    }
}
