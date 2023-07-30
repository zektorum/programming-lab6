package io.github.zektorum.command;

import java.io.Serializable;

public class WrongArgumentsException extends Exception implements Serializable {
    public WrongArgumentsException(String msg) {
        super(msg);
    }
}
