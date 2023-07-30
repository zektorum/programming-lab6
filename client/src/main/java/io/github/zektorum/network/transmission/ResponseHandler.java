package io.github.zektorum.network.transmission;

import io.github.zektorum.network.connection.DisconnectionException;
import io.github.zektorum.network.transmission.Response;
import io.github.zektorum.util.Deserializer;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ResponseHandler {
    private final int CAPACITY = 8096;
    private final int timeout = 5;
    private final SocketChannel socketChannel;

    public ResponseHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    private byte[] getAllBytes(ByteBuffer buffer) throws IOException {
        this.socketChannel.read(buffer);
        buffer.flip();

        List<Byte> byteList = new ArrayList<>();
        while (buffer.hasRemaining()) {
            byteList.add(buffer.get());
        }

        byte[] result = new byte[byteList.size()];
        int i = 0;
        for (Byte b : byteList) {
            result[i++] = b;
        }
        return result;
    }

    public void readResponse() throws DisconnectionException {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(CAPACITY);
            byte[] bytes;
            long currentTime = System.currentTimeMillis();
            do {
                if (System.currentTimeMillis() - currentTime >= this.timeout * 1000) {
                    throw new DisconnectionException("Время ожидания истекло.");
                }
                bytes = this.getAllBytes(byteBuffer);
            } while (bytes.length == 0);

            Deserializer deserializer = new Deserializer();
            Response response = (Response) deserializer.getObj(bytes);

            System.out.print(response.getMessage());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
