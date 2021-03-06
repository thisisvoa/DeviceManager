package com.geekcattle.netty.handler;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geekcattle.netty.bean.Client;
import com.geekcattle.netty.msg.AbsMsg;
import com.geekcattle.netty.msg.MSG_0x0002;
import com.geekcattle.utils.soket.msg.ClientManager;

/**
 * 
 * 链路登陆handler
 * @author sid
 *
 */
public class Handler0x0002 implements IHandler {

	Logger logger = LoggerFactory.getLogger(Handler0x0002.class);

	public void doHandle(AbsMsg m, ChannelHandlerContext ctx) {
		try {
			if (m instanceof MSG_0x0002) {
				logger.info("心跳消息:"+m.getHead().getMac());
				Client client = ClientManager.getClient(ctx);
				ClientManager.setClientLastTime(ctx, client);
			} else {
				logger.error("登录消息强转失败:"+m.toString());
			}
		} catch (Exception e) {
			logger.error("登录消息处理失败"+e);
		}
	}

}
