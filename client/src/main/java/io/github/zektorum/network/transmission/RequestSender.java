package io.github.zektorum.network.transmission;

import io.github.zektorum.command.BaseCommand;
import io.github.zektorum.command.CommandArgsArray;
import io.github.zektorum.data.person.Person;
import io.github.zektorum.network.transmission.Request;
import io.github.zektorum.util.Serializer;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestSender {
    public final int CAPACITY = 8096;
    private final SocketChannel socketChannel;


    public RequestSender(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public int sendRequest(Class<? extends BaseCommand> command, CommandArgsArray args, Person person)
            throws IOException, InstantiationException, IllegalAccessException {
        Serializer serializer = new Serializer();
        byte[] bytes = serializer.serialize(new Request(command.newInstance(), args, person));
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        int writeBytesCount;
        if (this.socketChannel.isConnected()) {
            writeBytesCount = this.socketChannel.write(buffer);
        } else {
            throw new ConnectException("Подключение с сервером разорвано.");
        }
        buffer.clear();
        return writeBytesCount;
    }
}
