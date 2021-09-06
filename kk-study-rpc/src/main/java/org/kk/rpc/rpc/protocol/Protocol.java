package org.kk.rpc.rpc.protocol;

import org.kk.rpc.rpc.Invoker;

import java.net.URI;

/**
 * @author Steven
 */
public interface Protocol {

    public Protocol export(URI uri, Invoker invoker);
}
