package com.geekcattle.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import com.geekcattle.netty.msg.AbsMsg;

public interface IHandler {

	void doHandle(AbsMsg m, ChannelHandlerContext ctx);

}
