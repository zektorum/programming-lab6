package io.github.zektorum.command.validation;

import io.github.zektorum.command.CommandArgsArray;

public class UpdateCommandArgsValidator implements ValidatorGroup {
    private CommandArgsArray args;
    public UpdateCommandArgsValidator(CommandArgsArray args) {
        this.args = args;
    }
    @Override
    public boolean validateGroup() {
        IdValidator idValidator = new IdValidator();
        return idValidator.validate(args);
    }
}
