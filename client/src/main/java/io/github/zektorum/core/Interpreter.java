package io.github.zektorum.core;

import io.github.zektorum.command.*;
import io.github.zektorum.data.person.creation.Director;
import io.github.zektorum.data.person.creation.PersonBuilder;
import io.github.zektorum.data.person.creation.PersonBuilderFromFile;
import io.github.zektorum.data.person.creation.PersonBuilderFromUserInput;
import io.github.zektorum.network.connection.ConnectionManager;
import io.github.zektorum.network.connection.ConnectionStatus;
import io.github.zektorum.network.connection.DisconnectionException;
import io.github.zektorum.network.connection.TimeoutException;
import io.github.zektorum.data.person.Person;
import io.github.zektorum.network.transmission.RequestSender;
import io.github.zektorum.network.transmission.ResponseHandler;
import io.github.zektorum.util.ConfigurationManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * Класс реализует интерпретатор, работающий в двух режимах.
 */
public class Interpreter {
    private final ConnectionManager connectionManager;
    private final ConfigurationManager configurationManager;
    private final int interpreterMode;
    private String scriptName;
    public static final List<String> scriptsStack = new LinkedList<>();
    public static Scanner scanner;
    public final static int USER_INPUT = 0;
    public final static int SCRIPT_INPUT = 1;

    {
        configurationManager = ConfigurationManager.loadData();
        connectionManager = new ConnectionManager(configurationManager.getHostname(), configurationManager.getPort(),
                configurationManager.getTimeout(), configurationManager.getDelta());
    }

    /**
     * Используется для создания интерпретатора в режиме ввода с клавиатуры.
     */
    public Interpreter() throws IOException {
        this.connectionManager.createConnection();
        this.interpreterMode = Interpreter.USER_INPUT;
        Interpreter.scriptsStack.add("Main");
    }

    /**
     * Используется для создания интерпретатора в режиме ввода из файла.
     *
     * @param scriptName название файла, содержащего команды
     */
    public Interpreter(String scriptName) throws IOException {
        this.connectionManager.createConnection();
        this.interpreterMode = Interpreter.SCRIPT_INPUT;
        this.scriptName = scriptName;
        Interpreter.scriptsStack.add(scriptName);
    }

    /**
     * Проверка на символ конца ввода.
    */
    public static void checkInput(Scanner userInput) {
        if (!userInput.hasNextLine()) {
           System.exit(0);
        }
    }

    public Person inputPerson() {
        PersonBuilder personBuilder;
        if (!Interpreter.scriptsStack.get(Interpreter.scriptsStack.size() - 1).equals("Main")) { // TODO: add method call instead of boolean expression
            personBuilder = new PersonBuilderFromFile(Interpreter.scanner);
        } else {
            personBuilder = new PersonBuilderFromUserInput();
        }
        Director director = new Director(personBuilder);
        return director.createPerson();
    }

    /**
     * Метод выводит в консоль стек вызовов скриптов.
     */
    private void printScriptsStack() {
        for (String script : Interpreter.scriptsStack) {
            System.out.printf("* %s\n", script);
        }
    }

    private void executeCommand(String commandName, List<String> tokens) throws TimeoutException, InterruptedException {
        SocketChannel channel = this.connectionManager.getConnection().getChannel();
        RequestSender requestSender = new RequestSender(channel);
        ResponseHandler responseHandler = new ResponseHandler(channel);
        BaseCommand command;
        try {
            if (commandName.equals("exit")) {
                System.out.println("Завершение работы...");
                System.exit(0);
            }
            if (CommandStorage.containsCommand(commandName)) {
                tokens.remove(0);
                Class<? extends BaseCommand> currentCommand = CommandStorage.get(commandName);
                Constructor<?> constructor = currentCommand.getConstructor();

                command = (BaseCommand) constructor.newInstance();
                Person person = null;
                if (command.personInputRequired()) {
                    person = inputPerson();
                }

                int writeBytesCount = requestSender.sendRequest(command.getClass(),
                        new CommandArgsArray(command.getArgsCount(), tokens), person);
                if (writeBytesCount > 0) {
                    responseHandler.readResponse();
                }
            } else {
                System.out.println("Команда не существует! Введите корректное название");
            }
        } catch (NoSuchMethodException e) {
            System.err.println("Ошибка! Класс команды не содержит необходимого конструктора.");
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("Ошибка создания отправляемой команды.");
        } catch (NumberFormatException | WrongArgumentsException e) {
            System.err.println("Ошибка! Неверно введены аргументы.\n");
            e.printStackTrace();
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Ошибка подключения.");
            e.printStackTrace();
        } catch (DisconnectionException e) {
            System.out.println("Подключение с сервером разорвано.");
            this.connectionManager.setConnectionStatus(ConnectionStatus.DOWN);
            this.connectionManager.retryConnection();
        }
    }

    /**
     * Возвращает нужный символ приглашения к вводу в зависимости от режима интерпретатора.
     *
     * @return строка, содержащая приглашение к вводу
     */
    private String getPromptString() {
        if (this.interpreterMode == USER_INPUT) {
            return "$";
        }
        return ">";
    }

    private Scanner getScanner() throws FileNotFoundException {
        if (this.interpreterMode == USER_INPUT) {
            return new Scanner(System.in);
        } else {
            return new Scanner(new File(this.scriptName));
        }
    }

    private boolean isRecursionCyclic(List<String> tokens) {
        return tokens.get(0).equals("execute_script") && tokens.size() == 2 &&
                tokens.get(1).equals(this.scriptName);
    }

    /**
     * Основной цикл приложения. В нём происходит парсинг пользовательского ввода/ввода из файла -
     * ввод разбивается на токены, происходит выборка команды и её последующий запуск с полученными
     * аргументами.
     * Пользователю выводится приглашение к вводу: PS1 при вводе с клавиатура и PS2 при вводе из файла.
     * Для запуска интерпретатора в режиме ввода с клавиатуры используется флаг io.github.zektorum.core.Interpreter.USER_INPUT,
     * а для ввода из файла - io.github.zektorum.core.Interpreter.SCRIPT_INPUT.
     */
    public void run() throws ConnectException {
        String promptString = getPromptString();

        Scanner scanner;
        try {
            scanner = getScanner();
        } catch(FileNotFoundException e) {
            System.out.println("Ошибка! Файл не существует\n");
            return;
        }

        Interpreter.scanner = scanner;
        List<String> tokens = new LinkedList<>();
        System.out.printf("%s ", promptString);
        String command, inputLine;

        try {
            while (scanner.hasNext()) {
                inputLine = scanner.nextLine().toLowerCase();

                if (this.interpreterMode == Interpreter.SCRIPT_INPUT) {
                    System.out.println(inputLine);
                }

                tokens.addAll(Arrays.asList(inputLine.split("\\s+")));
                command = tokens.get(0);

                if (isRecursionCyclic(tokens)) {
                    System.err.println("Ошибка! Попытка рекурсивного запуска файла из самого себя\n");
                    System.out.printf("%s ", promptString);
                    tokens.clear();
                    continue;
                }

                executeCommand(command, tokens);
                tokens.clear();

                if (this.interpreterMode == Interpreter.SCRIPT_INPUT && !scanner.hasNextLine()) {
                    break;
                }

                Interpreter.scanner = scanner;
                System.out.printf("%s ", promptString);
            }
        } catch (NoSuchElementException | InterruptedException e) {
            e.printStackTrace();
        }
        catch (TimeoutException e) {
            System.err.println("Не удалось восстановить соединение.");
            System.exit(1);
        }
        Interpreter.scriptsStack.remove(scriptsStack.size() - 1);
    }
}
