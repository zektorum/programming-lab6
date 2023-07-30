package io.github.zektorum.network.connection;

import java.io.Closeable;
import java.net.SocketAddress;

public abstract class AbstractConnection {
    private final String hostname;
    private final int port;
    private int timeout;

    protected AbstractConnection (String hostname, int port, int timeout) {
        this.hostname = hostname;
        this.port = port;
        this.timeout = timeout;
    }

    protected AbstractConnection(String hostname, int port) {
        this(hostname, port, 5);
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return this.timeout;
    }
}
