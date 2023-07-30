package io.github.zektorum.network.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Connection {
    private static SocketChannel socketChannel = null;

    private Connection() {}

    public static SocketChannel open(int port) throws IOException {
        try {
            if (socketChannel == null) {
                socketChannel = SocketChannel.open(new InetSocketAddress("localhost", port));
            }
        } catch (ConnectException e) {
            System.err.println("Ошибка! Сервер недоступен");
            System.exit(1);
        }
        return socketChannel;
    }
}
