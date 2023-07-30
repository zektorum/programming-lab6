package io.github.zektorum.io;

import java.io.*;

public class Serializer {
    public byte[] serialize(Serializable obj) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(outputStream)) {
            out.writeObject(obj);
            byte[] bytes = outputStream.toByteArray();
            out.close();
            outputStream.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
