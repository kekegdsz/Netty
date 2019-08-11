package com.kekegdsz.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //websocket 基于http协议，所以要有http编解码器
        pipeline.addLast(new HttpServerCodec());
        //对于大数据流支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage进行聚合，聚合成fullHttpRequest或FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        // ====================以上用于支持http协议=====================================

        // websocket 服务器处理的协议，用于客户端处理访问的路径
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定义handler
        pipeline.addLast(new ChatHandler());
    }
}
