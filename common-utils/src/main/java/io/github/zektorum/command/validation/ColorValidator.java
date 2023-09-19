package io.github.zektorum.command.validation;

import io.github.zektorum.command.CommandArgsArray;
import io.github.zektorum.data.person.fields.Color;

public class ColorValidator implements Validator {
    @Override
    public boolean validate(CommandArgsArray args) {
        String color = args.getArg(0);
        try {
            Color.EyeColor.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
