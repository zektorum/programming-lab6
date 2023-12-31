package io.github.zektorum.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
