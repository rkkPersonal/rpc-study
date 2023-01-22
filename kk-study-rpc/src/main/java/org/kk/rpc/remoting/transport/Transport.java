package org.kk.rpc.remoting.transport;

import org.kk.rpc.remoting.handler.TrpcServerHandler;
import org.kk.rpc.remoting.server.Server;
import org.kk.rpc.rpc.codec.TrpcCodec;

import java.net.URI;

/**
 * @author Steven
 */
public interface Transport {

    Server start(URI uri, TrpcCodec trpcCodec, TrpcServerHandler trpcServerHandler);
}
