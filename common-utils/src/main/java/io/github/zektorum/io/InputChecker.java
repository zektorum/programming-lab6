package io.github.zektorum.io;

import java.util.Scanner;

public interface InputChecker {
    default void checkInput(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            System.exit(0);
        }
    }
}
