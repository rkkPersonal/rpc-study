package org.kk.rpc.rpc.proxy;

import org.kk.rpc.rpc.Invoker;
import org.kk.rpc.rpc.RpcInvocation;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Steven
 */
public class ProxyFactory {

    public static Object getProxy(Invoker invoker, Class<?>[] interfaces) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, new InvokerInvocationHandler(invoker));
    }

    public static Invoker getInvoker(Object proxy, Class type) {
        return new Invoker() {
            @Override
            public Class getInterface() {
                return type;
            }

            @Override
            public Object invoke(RpcInvocation rpcInvocation) throws Exception {
                // 反射调用对象的方法
                Method method = proxy.getClass().getMethod(rpcInvocation.getMethodName(), rpcInvocation.getParameterTypes());
                return method.invoke(proxy, rpcInvocation.getArguments());
            }
        };
    }
}
