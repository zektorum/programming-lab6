package io.github.zektorum.network.transmission;

import io.github.zektorum.command.BaseCommand;
import io.github.zektorum.command.CommandArgsArray;
import io.github.zektorum.data.person.Person;

import java.io.Serializable;

public class Request implements Serializable {
    private BaseCommand command;
    private CommandArgsArray args;
    private Person person;

    public Request() {}

    public Request(BaseCommand command, CommandArgsArray args, Person person) {
        this.command = command;
        this.args = args;
        this.person = person;
    }

    public BaseCommand getCommand() {
        return this.command;
    }

    public CommandArgsArray getArgs() {
        return this.args;
    }

    public Person getPerson() {
        return this.person;
    }
}
