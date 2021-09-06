package org.kk.rpc.remoting.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.kk.rpc.remoting.handler.NettyHandler;

import java.net.InetSocketAddress;
import java.net.URI;

/**
 * @author Steven
 */
public class Netty4Server implements Server {


    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup worker = new NioEventLoopGroup();

    @Override
    public void start(URI uri) {

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    // 指定所使用的nio传输channel
                    .channel(NioServerSocketChannel.class)
                    // 指定要监听地址
                    .localAddress(new InetSocketAddress(uri.getHost(), uri.getPort()))
                    // 添加handler - 有链接之后 处理逻辑
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 协议编解码 （RpcInvocation）
                            ch.pipeline().addLast(new NettyHandler());

                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("完成端口绑定和服务器启动");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
