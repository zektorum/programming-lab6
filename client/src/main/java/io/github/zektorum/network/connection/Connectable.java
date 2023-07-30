package io.github.zektorum.network.connection;

import java.io.IOException;

public interface Connectable {
    SocketConnection createConnection() throws IOException;
}
