package io.github.zektorum.command.validation;

import io.github.zektorum.command.CommandArgsArray;

public interface Validator {
    public boolean validate(CommandArgsArray args);
}
