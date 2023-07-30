package io.github.zektorum.network.transmission;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestHandler implements Runnable {
    private final SocketChannel channel;

    public RequestHandler(SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        System.out.printf("Connected from %s\n", this.channel.socket().getInetAddress());
        ByteBuffer buffer = ByteBuffer.allocate(65607);
        try {
            while (this.channel.read(buffer) > 0) {
                System.out.println(new String(buffer.array()).trim());
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
