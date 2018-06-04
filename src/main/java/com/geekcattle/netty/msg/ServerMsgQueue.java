package com.geekcattle.netty.msg;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * 消息队列
 * 
 * @author nifeng
 *
 */
public class ServerMsgQueue {
	/**
	 * 接收数据队列
	 */
	private static LinkedBlockingQueue<ReciPackBean> rec_queue = new LinkedBlockingQueue<ReciPackBean>();

	public static LinkedBlockingQueue<ReciPackBean> getRecqueue() {
		return rec_queue;
	}
}
