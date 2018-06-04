package com.geekcattle.netty.server;

import com.geekcattle.utils.soket.msg.TCPCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2018/6/4/004.
 */
public class TCPServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    private TCPServerHandler tcpServerHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast("tcpMsgEncoder", new TCPCodec());
        pipeline.addLast("tcpMsgDecoder", new TCPCodec());
        pipeline.addLast("tcpMsgHandler", tcpServerHandler);
    }
}
