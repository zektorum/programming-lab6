package io.github.zektorum.network.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.NetworkChannel;

public abstract class SocketConnection extends AbstractConnection {
    private final NetworkChannel channel;

    protected SocketConnection(NetworkChannel channel, String hostname, int port) throws IOException {
        super(hostname, port);
        this.channel = channel;
    }

    protected SocketConnection (NetworkChannel channel, String hostname, int port, int timeout) throws IOException {
        super(hostname, port, timeout);
        this.channel = channel;
    }
}
