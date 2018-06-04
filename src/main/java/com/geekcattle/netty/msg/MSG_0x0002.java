package com.geekcattle.netty.msg;


import com.geekcattle.utils.soket.msg.Converter;
import com.geekcattle.utils.utils.LogUtil;

import java.util.Arrays;

/**
 * 
 * ClassName: MSG_0x0002 
 * Reason: 心跳消息 
 * date: 2015年5月11日 下午4:01:48 
 *
 * @author nifeng
 */
public class MSG_0x0002 extends AbsMsg {

	private static final long serialVersionUID = 1L;

	
	
	@Override
	public String toString() {
		return "MSG_0x0002 [head=" + head + "]";
	}

	@Override
	protected int getMsgID() {
		return MessageID.MSG_0x0002;
	}

	@Override
	protected int getBodylen() {
		return 0;
	}

	@Override
	protected byte[] bodytoBytes() {
		return new byte[0];
	}

	@Override
	protected boolean bodyfrombytes(byte[] b) {
		boolean resultState = false;
		int offset = 0;
		try {

			resultState = true;
		} catch (Exception e) {
			LogUtil.getInstance().getLogger(MSG_0x0001.class).error("登录消息fromBytes转换异常",e);
			e.printStackTrace();
		}
		return resultState;
	}
}
