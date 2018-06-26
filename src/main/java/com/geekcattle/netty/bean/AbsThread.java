package com.geekcattle.netty.bean;

/**
 * 
 * 线程抽象类
 * @author geekcattle
 *
 */
public abstract class AbsThread {

	public boolean isRun = false;
	public void run() {
		run(0, 0);
	}
	public void run(long delay, long period) {
		if (isRun)
			return;
		isRun = true;
		runThread(delay, period);
	}

	protected abstract void runThread(long delay, long period);
	
	public abstract void stop();

}
