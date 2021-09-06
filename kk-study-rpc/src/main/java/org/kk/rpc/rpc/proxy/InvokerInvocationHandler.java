package org.kk.rpc.rpc.proxy;

import org.kk.rpc.rpc.Invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvokerInvocationHandler implements InvocationHandler {

    public InvokerInvocationHandler(Invoker invoker) {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
