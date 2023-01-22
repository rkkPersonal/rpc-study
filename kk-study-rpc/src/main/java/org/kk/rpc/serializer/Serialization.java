package org.kk.rpc.serializer;

/**
 * @author Steven
 */
public interface Serialization {

    byte[] serialize(Object output) throws Exception;

    Object deserialize(byte[] input, Class clazz) throws Exception;
}
