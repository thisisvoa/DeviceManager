package com.geekcattle.netty.handler;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geekcattle.netty.cache.MsgCache;
import com.geekcattle.netty.msg.AbsMsg;
import com.geekcattle.netty.msg.MSG_0x0003;

/**
 * 
 * 链路登陆handler
 * @author nifeng
 *
 */
public class Handler0x0003 implements IHandler {

	Logger logger = LoggerFactory.getLogger(Handler0x0003.class);

	public void doHandle(AbsMsg m, ChannelHandlerContext ctx) {
		try {
			if (m instanceof MSG_0x0003) {
				MSG_0x0003 msg = (MSG_0x0003)m;
				logger.info("通用应答："+msg.getMsgid());
				MsgCache.getInstance().remove(msg.getMsgid()+";"+msg.getHead().getSeq());
			} else {
				logger.error("登录消息强转失败:"+m.toString());
			}
		} catch (Exception e) {
			logger.error("登录消息处理失败"+e);
		}
	}

}
