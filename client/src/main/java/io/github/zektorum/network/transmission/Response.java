package io.github.zektorum.network.transmission;

import java.io.Serializable;

public class Response implements Serializable {
    private String message;

    public Response() {}

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
