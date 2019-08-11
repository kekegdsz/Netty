package com.kekegdsz.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WSServer {

    /**
     * 启动netty服务器
     */
    public static void startNetty() throws InterruptedException {
        NioEventLoopGroup mainGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();


        ServerBootstrap server = new ServerBootstrap();
        server.group(mainGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitializer())
                .bind(8088).addListener(future1 -> {
            if (future1.isSuccess()) {
                System.out.println("端口绑定成功!");
            } else {
                System.err.println("端口绑定失败!");
            }
        });

    }

}
