package com.geekcattle.netty.handler;

import com.geekcattle.utils.soket.msg.ClientManager;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.geekcattle.netty.msg.*;
import com.geekcattle.netty.server.TCPServer;
import com.geekcattle.SpringUtil;

/**
 * 
 * 链路登陆handler
 * @author nifeng
 *
 */
public class Handler0x2001 implements IHandler {

	Logger logger = LoggerFactory.getLogger(Handler0x2001.class);

	@Autowired(required = true)
	private ClientManager clientManager;

	@Bean

	public SpringUtil springUtil2(){
		return new SpringUtil();
	}
	public void doHandle(AbsMsg m, ChannelHandlerContext ctx) {
		try {
			if (m instanceof MSG_0x2001) {
				MSG_0x2001 msg = (MSG_0x2001) m;
				MsgFutureManager msgFutureManager = (MsgFutureManager) SpringUtil.getBean("msgFutureManager");
				SessionFutureKey sessionFutureKey=new SessionFutureKey();
				sessionFutureKey.setDeviceId(msg.getHead().getMac());
				sessionFutureKey.setDeviceType("A");
				sessionFutureKey.setSeq(m.getHead().getSeq());
				msgFutureManager.put(sessionFutureKey,m);
				logger.info("位置请求:"+msg.toString());
				/*//通用应答
				MSG_0x3003 response = new MSG_0x3003();
				response.setMsgid(m.getHead().getMsgid());
				response.setState((byte)1);
				response.getHead().setSeq(m.getHead().getSeq());*/
				//TCPServer.getSingletonInstance().sendWithoutCache(response);
			} else {
				logger.error("登录消息强转失败:"+m.toString());
			}
		} catch (Exception e) {
			logger.error("登录消息处理失败"+e);
		}
	}

}
