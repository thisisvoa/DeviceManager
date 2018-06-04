package com.geekcattle.netty.server.thread;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geekcattle.utils.soket.msg.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 检查长时间未活跃的客户端
 * 
 * @author cuipengfei
 *
 */
public class CheckClientThread extends AbsThread {

	private static final Logger logger = LoggerFactory
			.getLogger(CheckClientThread.class);

	@Autowired
	private ClientManager clientManager;
	@Autowired(required = false)
	private Integer clientdelay;
	@Autowired(required = false)
	private Integer period;

	private Timer timer = new Timer();

	public void runThread() {
		timer = new Timer();
		try {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					try{
						ClientManager.checkClient();
					}catch(Exception e){
						logger.error("检查登陆客户端异常:",e);
					}
				}
			}, getClientdelay() * 1000, period * 1000);
		} catch (Exception e) {
			logger.info("检查客户端线程异常：",e);
			e.printStackTrace();
			
		}

	}

	public Integer getClientdelay() {
		return clientdelay;
	}

	public void setClientdelay(Integer clientdelay) {
		this.clientdelay = clientdelay;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	@Override
	public void runThread(long delay, long period) {
		timer = new Timer();
		try {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						clientManager.checkClient();
					} catch (Exception e) {
						logger.error("检查登陆客户端异常:", e);
					}
				}
			}, delay * 1000, period * 1000);
		} catch (Exception e) {
			logger.info("检查客户端线程异常：", e);
			e.printStackTrace();

		}
	}
}
