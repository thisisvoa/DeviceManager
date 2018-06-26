package com.geekcattle.netty.communication;

import com.geekcattle.netty.msg.AbsMsg;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *  Copyright 2017 the original author or authors hangzhou Reformer
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @author geekcattle
 * @create 2017-05-08
**/
public class TCPMessageSender implements ISender<AbsMsg> {
    private static final Logger LOG = LoggerFactory.getLogger(TCPMessageSender.class);
    private Channel channel;

    public TCPMessageSender() {
    }

    public TCPMessageSender(Channel channel) {
        this.channel = channel;
    }

    @Override
    public ChannelFuture send(AbsMsg msg) {
        if (channel == null) {
            LOG.error("Send TCP message failed, the channel is not connected.");
            return null;
        }

        if (msg == null) {
            LOG.error("Send TCP message to {} failed, the message packet is null.", channel.remoteAddress());
            return null;
        }
        return channel.writeAndFlush(msg);
    }

    @Override
    public void close() {
        if (channel != null)
            channel.close().awaitUninterruptibly();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
