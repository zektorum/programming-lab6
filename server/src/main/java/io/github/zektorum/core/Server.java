package io.github.zektorum.core;

import io.github.zektorum.network.transmission.Request;
import io.github.zektorum.network.transmission.Response;
import io.github.zektorum.util.CommandHandler;
import io.github.zektorum.util.Deserializer;
import io.github.zektorum.util.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Server {
    private final int port;
    private final Selector selector = Selector.open();

    public Server(int port) throws IOException {
        this.port = port;
    }

    public void accept(ServerSocketChannel channel) {
        SocketChannel socketChannel;
        try {
            socketChannel = channel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(this.selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            System.out.printf("Connected from %s\n", socketChannel.socket().getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(SocketChannel channel, ByteArrayOutputStream response) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(response.toByteArray());
            channel.write(buffer);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    private byte[] getBytesFromClient(ByteBuffer buffer) {
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

    private Request getRequest(SelectionKey key) throws IOException, ClassNotFoundException {
        int CAPACITY = 8096;
        ByteBuffer byteBuffer = ByteBuffer.allocate(CAPACITY);
        SocketChannel selectableChannel = (SocketChannel) key.channel();

        int bytesRead = selectableChannel.read(byteBuffer);
        if (bytesRead < 1) {
            return null;
        }

        byte[] bytes = this.getBytesFromClient(byteBuffer);
        Deserializer deserializer = new Deserializer();
        return (Request) deserializer.getObj(bytes);
    }

    public void start() {
        try (ServerSocketChannel channel = ServerSocketChannel.open();
                ServerSocket serverSocket = channel.socket()) {
            serverSocket.bind(new InetSocketAddress(port));
            channel.configureBlocking(false);
            channel.register(this.selector, SelectionKey.OP_ACCEPT);
            String responseMessage;
            Serializer serializer = new Serializer();

            while (true) {
                try {
                    int readyChannels = selector.select(500);
                        if (readyChannels == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                    SocketChannel selectableChannel;
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        if (key.isAcceptable()) {
                            this.accept(channel);
                        }

                        if (key.isReadable()) {
                            selectableChannel = (SocketChannel) key.channel();
                            Request request = this.getRequest(key);
                            if (request != null) {
                                System.out.printf("Request: %s\n", request.getCommand());
                                CommandHandler handler = new CommandHandler();
                                responseMessage = handler.processRequest(request);
                                System.out.printf("Response message: \"%s\"\n", responseMessage);

                                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                                bytes.write(serializer.serialize(new Response(responseMessage)));
                                this.sendResponse(selectableChannel, bytes);
                            }
                        }
                    }
                    keyIterator.remove();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
