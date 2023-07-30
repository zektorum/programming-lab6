package io.github.zektorum.network.connection;

public class TimeoutException extends Exception {
    public TimeoutException(String message) {
        super(message);
    }
}
