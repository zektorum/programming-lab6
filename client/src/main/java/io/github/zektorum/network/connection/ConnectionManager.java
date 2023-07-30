package io.github.zektorum.network.connection;

import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetSocketAddress;

public class ConnectionManager implements Connectable, Reconnectable {
    private static TcpConnection connection;
    private final String host;
    private final int port;
    private final int timeout;
    private final int delta;
    private ConnectionStatus status = ConnectionStatus.DOWN;

    public ConnectionManager(String host, int port, int timeout, int delta) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.delta = delta;
    }

    public ConnectionManager(int port, int timeout, int delta) {
        this("localhost", port, timeout, delta);
    }

    public SocketConnection createConnection() throws IOException {
        this.status = ConnectionStatus.IN_PROGRESS;
        try {
            if (connection == null) {
                connection = new TcpConnection(this.host, this.port);
                connection.getChannel().connect(new InetSocketAddress(this.host, this.port));
            }
        } catch (ConnectException e) {
            this.status = ConnectionStatus.DOWN;
            System.err.println("Не удалось установить соединение с сервером. Повторите попытку.");
            System.exit(1);
        } catch (BindException e) {
            this.status = ConnectionStatus.DOWN;
            System.err.println("Ошибка! Адрес уже используется.");
            System.exit(1);
        }
        this.status = ConnectionStatus.ESTABLISHED;
        return connection;
    }

    public SocketConnection reconnect() throws IOException {
        this.status = ConnectionStatus.IN_PROGRESS;
        try {
            connection = new TcpConnection(this.host, this.port);
            TcpConnection.openChannel();
            connection.getChannel().connect(new InetSocketAddress(this.host, this.port));
        } catch (IOException e) {
            this.status = ConnectionStatus.DOWN;
            throw new IOException();
        }
        this.status = ConnectionStatus.ESTABLISHED;
        return connection;
    }

    public TcpConnection getConnection() {
        return connection;
    }

    public void setConnectionStatus(ConnectionStatus status) {
        this.status = status;
    }

    public ConnectionStatus getConnectionStatus() {
        return this.status;
    }

    public void retryConnection() throws TimeoutException, InterruptedException {
        int connectionTimeout = this.timeout;
        int ATTEMPTS = 3;
        for (int i = 0; i < ATTEMPTS; ++i) {
            try {
                System.out.printf("Попытка повторного подключения #%d\n", i + 1);
                connection.close();
                this.reconnect();
                break;
            } catch (IOException e) {
                Thread.sleep((long) connectionTimeout * 1000);
                connectionTimeout += this.delta;
            }
        }
        if (this.status == ConnectionStatus.ESTABLISHED) {
            System.out.println("Соединение восстановлено!");
        } else {
            throw new TimeoutException("Время ожидания истекло.");
        }
    }
}
