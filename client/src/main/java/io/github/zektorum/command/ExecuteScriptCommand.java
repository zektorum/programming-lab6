package io.github.zektorum.command;

import io.github.zektorum.core.Interpreter;
import io.github.zektorum.data.person.Person;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Реализация команды execute_script.
 */
public class ExecuteScriptCommand extends BaseCommand {
    public ExecuteScriptCommand(Scanner scanner) {}

    public Person execute(Scanner scanner, CommandArgsArray args) {
        String filename;
        if ((filename = args.getArg(0)) == null){
            System.out.println("Ошибка! Некорректно задан аргумент\n");
            return null;
        }

        if (!(new File(filename).exists())) {
            System.out.println("Некорректное имя! Файл не существует\n");
            return null;
        }
        if (!new File(filename).canRead()) {
            System.out.println("Отсутствуют права на чтение!\n");
            return null;
        }
        if (Interpreter.scriptsStack.contains(filename)) {
            System.out.println("Ошибка! Попытка циклического запуска скрипта\n");
            return null;
        }
        try {
            Interpreter interpreter = new Interpreter(filename);
            interpreter.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getUsage() {
        return "execute_script filename";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды " +
                "в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public int getArgsCount() {
        return 1;
    }
}
