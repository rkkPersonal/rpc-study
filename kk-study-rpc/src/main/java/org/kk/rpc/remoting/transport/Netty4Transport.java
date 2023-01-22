package org.kk.rpc.remoting.transport;

import org.kk.rpc.remoting.handler.TrpcServerHandler;
import org.kk.rpc.remoting.server.Netty4Server;
import org.kk.rpc.remoting.server.Server;
import org.kk.rpc.rpc.codec.TrpcCodec;

import java.net.URI;

/**
 * @author Steven
 */
public class Netty4Transport implements Transport {

    @Override
    public Server start(URI uri, TrpcCodec trpcCodec, TrpcServerHandler trpcServerHandler) {
        Netty4Server netty4Server = new Netty4Server(trpcCodec,trpcServerHandler);
        netty4Server.start(uri);
        return netty4Server ;
    }
}
