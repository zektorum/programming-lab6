package io.github.zektorum.util;

import io.github.zektorum.network.transmission.Request;

public class CommandHandler {
    public CommandHandler() {}

    public String processRequest(Request request) {
        return request.getCommand().execute(request.getArgs(), request.getPerson());
    }
}
