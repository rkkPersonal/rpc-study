package org.kk.rpc.serializer;

/**
 * @author Steven
 */
public class JsonSerializer implements Serialization{
    @Override
    public byte[] serialize(Object output) throws Exception {
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] input, Class clazz) throws Exception {
        return null;
    }
}
