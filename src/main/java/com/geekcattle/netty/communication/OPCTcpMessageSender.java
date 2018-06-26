package com.geekcattle.netty.communication;

import com.geekcattle.utils.soket.msg.ClientManager;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;


/**
 *  Copyright 2017 the original author or authors hangzhou Reformer 
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @author geekcattle
 * @create 2017-05-08
**/
public class OPCTcpMessageSender extends TCPMessageSender {

    Logger logger = LoggerFactory.getLogger(OPCTcpMessageSender.class);
    @Autowired(required = true)
    private ClientManager clientManager;

    public OPCTcpMessageSender(Channel channel) {
        super(channel);
    }

    public OPCTcpMessageSender() {
        super();
    }




    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
}
