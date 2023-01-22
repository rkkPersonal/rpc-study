package org.kk.rpc.rpc.protocol;

import org.kk.rpc.remoting.handler.TrpcServerHandler;
import org.kk.rpc.remoting.transport.Transport;
import org.kk.rpc.rpc.Invoker;
import org.kk.rpc.rpc.RpcInvocation;
import org.kk.rpc.rpc.codec.TrpcCodec;
import org.kk.rpc.serializer.Serialization;
import org.kk.rpc.utils.SpiUtils;
import org.kk.rpc.utils.URIUtils;

import java.net.URI;

/**
 * @author Steven
 */
public class KkRpcProtocol implements Protocol{


    @Override
    public void export(URI exportUri, Invoker invoker) {

        // 找到序列化
        String serializationName = URIUtils.getParam(exportUri, "serialization");
        Serialization serialization = SpiUtils.getServiceImpl(serializationName, Serialization.class);

        // 1. 编解码器
        TrpcCodec trpcCodec = new TrpcCodec();
        trpcCodec.setDecodeType(RpcInvocation.class);
        trpcCodec.setSerialization(serialization);
        // 2. 收到请求处理器
        TrpcServerHandler trpcServerHandler = new TrpcServerHandler();
        trpcServerHandler.setInvoker(invoker);
        trpcServerHandler.setSerialization(serialization);
        // 3. 底层网络框架
        String transporterName = URIUtils.getParam(exportUri, "transporter");
        Transport transporter =  SpiUtils.getServiceImpl(transporterName, Transport.class);
        // 4. 启动服务
        transporter.start(exportUri, trpcCodec, trpcServerHandler);

    }
}
