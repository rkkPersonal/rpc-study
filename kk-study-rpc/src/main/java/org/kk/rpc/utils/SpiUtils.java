package org.kk.rpc.utils;

import org.kk.rpc.serializer.Serializer;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Steven
 */
public class SpiUtils {

    public static <T>T getServiceImpl(String name, Class<T> classType) {
        ServiceLoader load = ServiceLoader.load(classType, Thread.currentThread().getContextClassLoader());
        Iterator iterator = load.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            String simpleName = obj.getClass().getSimpleName();
            if (simpleName.equals(name)) {
                return(T) obj;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        Serializer jsonSerializer = SpiUtils.getServiceImpl("JsonSerializer", Serializer.class);
        System.out.println(jsonSerializer);
    }

}
