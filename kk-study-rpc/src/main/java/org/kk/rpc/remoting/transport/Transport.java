package org.kk.rpc.remoting.transport;

import org.kk.rpc.remoting.server.Server;

import java.net.URI;

/**
 * @author Steven
 */
public interface Transport {

    Server start(URI uri);
}
