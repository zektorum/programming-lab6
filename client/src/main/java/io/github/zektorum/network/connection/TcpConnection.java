package io.github.zektorum.network.connection;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.SocketChannel;

public class TcpConnection extends SocketConnection implements Closeable {
    private static SocketChannel channel;

    static {
        openChannel();
    }

    public TcpConnection(String hostname, int port) throws IOException {
        super(TcpConnection.channel, hostname, port);
    }

    public static void openChannel() {
        try {
            channel = SocketChannel.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SocketChannel getChannel() {
        return TcpConnection.channel;
    }

    public void close() throws IOException {
        TcpConnection.channel.close();
    }
}
