package io.github.zektorum.command.validation;

import io.github.zektorum.command.CommandArgsArray;

public class CountGreaterThanEyeColorCommandArgsValidator implements ValidatorGroup {
    private final CommandArgsArray args;

    public CountGreaterThanEyeColorCommandArgsValidator(CommandArgsArray args) {
        this.args = args;
    }

    @Override
    public boolean validateGroup() {
        ColorValidator validator = new ColorValidator();
        return validator.validate(args);
    }
}
