package org.kk.rpc.rpc.proxy;

import org.kk.rpc.rpc.Invoker;
import org.kk.rpc.rpc.RpcInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Steven
 */
public class InvokerInvocationHandler implements InvocationHandler {

    private Invoker invoker;

    public InvokerInvocationHandler(Invoker invoker) {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(proxy)){
            return method.invoke(proxy,args);
        }

        RpcInvocation rpcInvocation = new RpcInvocation();
        return invoker.invoke(rpcInvocation);
    }
}
