package io.github.zektorum.network.connection;

import java.io.IOException;

public interface Reconnectable {
    SocketConnection reconnect() throws IOException;
}
