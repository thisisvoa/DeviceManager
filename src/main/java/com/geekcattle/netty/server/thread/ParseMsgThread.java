package com.geekcattle.netty.server.thread;

import com.geekcattle.netty.handler.HandlerFactory;
import com.geekcattle.netty.handler.IHandler;
import com.geekcattle.netty.msg.AbsMsg;
import com.geekcattle.netty.msg.MsgFactory;
import com.geekcattle.netty.msg.MsgHeader;
import com.geekcattle.netty.msg.ReciPackBean;
import com.geekcattle.utils.soket.msg.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * 
 * 处理消息线程
 * @author sid
 *
 */
public class ParseMsgThread extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(ParseMsgThread.class);

	private ReciPackBean rpb;

	public ParseMsgThread(ReciPackBean rpb) {
		this.rpb = rpb;
	}

	@Override
	public void run() {
		try {
			// 转码
			byte[] msgbytes = rpb.getMsgbytes();

			if (null != msgbytes) {
				//转码
				byte[] decode = decode(msgbytes);
				rpb.setMsgbytes(decode);

				// 消息头解析
				MsgHeader head = headFromBytes(msgbytes);
				if (head == null) {
					return;
				}
				logger.info("消息头：" + head.toString());

				// 生成消息后产生handler
				AbsMsg msg = MsgFactory.genMsg(head, decode);
				System.out.println(head.toString());
				if (msg == null) {
					logger.error(Integer.toHexString(head.getMsgid()) + "消息不存在");
					return;
				}
				logger.info("消息体：" + msg.toString());
				// 交给对应handler处理
				IHandler handler = HandlerFactory.getHandler(msg);
				if (handler != null) {
					handler.doHandle(msg, rpb.getChannel());
				}
			} else {
				logger.error("接受消息队列处理数据rpb.getMsgbytes为空！");
			}


		} catch (Exception e) {
			logger.error("接受消息队列处理数据错误", e);
		}
	}

	/**
	 * 消息头解析
	 * 
	 * @return
	 */
	private MsgHeader headFromBytes(byte[] b) {
		ByteBuffer buffer1 = ByteBuffer.wrap(b);
		byte[] head_body = new byte[Constants.HEAD_LENGTH];
		buffer1.position(0);
		buffer1.get(head_body);

		MsgHeader head = new MsgHeader();
		if (!head.frombytes(head_body))
			return null;// 消息头属性解析有误
		return head;
	}

	/**
	 * 解码转义
	 * 
	 * @param b
	 * @return
	 */
	private byte[] decode(byte[] b) {
		ByteBuffer buffer = ByteBuffer.allocate(5 * 1024 * 1024);
		ByteBuffer buffer1 = ByteBuffer.wrap(b);
		buffer.position(0);
		while (buffer1.remaining() > 0) {

			byte d = buffer1.get();
			if (d == 0x5a) {
				if(buffer1.remaining()>0){
					byte e = buffer1.get();
					if (e == 0x02)
						buffer.put((byte) 0x5a);
					else if (e == 0x01)
						buffer.put((byte) 0x5b);
				}
				else{
					System.out.println("0x5d remaining!");
				}
			} else if (d == 0x5e) {
				if(buffer1.remaining()>0){
					byte e = buffer1.get();
					if (e == 0x02)
						buffer.put((byte) 0x5e);
					else if (e == 0x01)
						buffer.put((byte) 0x5d);
				}
				else{
					System.out.println("无buffer");
				}
			} else {
				buffer.put(d);
			}
		}

		byte[] result = new byte[buffer.position()];
		buffer.position(0);
		buffer.get(result);
		return result;
	}

}
