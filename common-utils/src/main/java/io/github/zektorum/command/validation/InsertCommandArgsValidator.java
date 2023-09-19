package io.github.zektorum.command.validation;

import io.github.zektorum.command.CommandArgsArray;

public class InsertCommandArgsValidator implements ValidatorGroup {
    private CommandArgsArray args;

    public InsertCommandArgsValidator(CommandArgsArray args) {
        this.args = args;
    }
    @Override
    public boolean validateGroup() {
        IdValidator validator = new IdValidator();
        return validator.validate(args);
    }
}
