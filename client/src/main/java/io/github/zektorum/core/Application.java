package io.github.zektorum.core;

import java.io.IOException;
import java.net.ConnectException;

public class Application {
    private final String[] commandLineArgs;

    public Application(String[] args) {
        this.commandLineArgs = args;
    }

    public void run() {
        try {
            Interpreter interpreter = new Interpreter();
            interpreter.run();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
