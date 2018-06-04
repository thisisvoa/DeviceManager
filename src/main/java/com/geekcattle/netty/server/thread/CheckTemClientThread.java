package com.geekcattle.netty.server.thread;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geekcattle.utils.soket.msg.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 检查未登陆的客户端
 * 
 * @author cuipengfei
 *
 */
public class CheckTemClientThread extends AbsThread {

	private static final Logger logger = LoggerFactory.getLogger(CheckTemClientThread.class);

	@Autowired
	private ClientManager clientManager;

	@Autowired(required = false)
	private Integer delay;

	@Autowired(required = false)
	private Integer period;

	private Timer timer = new Timer();

	public void runThread() {
		timer = new Timer();
		try{
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					clientManager.checkTempClient();
				}
			}, delay * 1000, period * 1000);
		}catch(Exception e){
			logger.error("检查临时客户端异常：",e);
		}

	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
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
		try{
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					clientManager.checkTempClient();
				}
			}, delay * 1000, period * 1000);
		}catch(Exception e){
			logger.error("检查临时客户端异常：",e);
		}
	}
}
