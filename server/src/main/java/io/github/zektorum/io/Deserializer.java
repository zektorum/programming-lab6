package io.github.zektorum.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Deserializer {
    public Object getObj(byte[] bytes) throws ClassNotFoundException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             ObjectInput in = new ObjectInputStream(inputStream)) {
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
