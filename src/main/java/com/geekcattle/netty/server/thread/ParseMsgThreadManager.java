package com.geekcattle.netty.server.thread;

import com.geekcattle.netty.bean.AbsThread;
import com.geekcattle.netty.handler.HandlerFactory;
import com.geekcattle.netty.msg.ReciPackBean;
import com.geekcattle.netty.msg.ServerMsgQueue;
import com.geekcattle.utils.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池（处理消息）管理
 *
 * @author nifeng
 */
public class ParseMsgThreadManager extends AbsThread {

    private static final Logger logger = LoggerFactory
            .getLogger(ParseMsgThreadManager.class);



    static ParseMsgThreadManager obj;
    private Integer corePoolSize;
    private Integer maximunPoolSize;
    private Integer keepAliveTime;
    private Integer queueSize;
    @Autowired
    private HandlerFactory handlerFactory;
    private ThreadPoolExecutor threadPool;

    private volatile boolean isStart = true;

    public ParseMsgThreadManager(int corePoolSize, int maximunPoolSize, int keepAliveTime, int queueSize) {

        threadPool = new ThreadPoolExecutor(corePoolSize, maximunPoolSize,
                keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Override
    public void runThread(long delay, long period) {
        isRun = true;
        isStart = true;

        new Thread(new ParseThreadManage()).start();
        logger.info("服务器消息处理启动完成");

    }

    class ParseThreadManage implements Runnable {

        public void run() {
            while (isStart) {
                ReciPackBean rpb = null;
                try {
                    rpb = ServerMsgQueue.getRecqueue().take();
                    if (null != rpb) {
                        threadPool.execute(new ParseMsgThread(rpb,handlerFactory));
                    } else {
                        logger.error("队列消息为空，不执行解析线程");
                    }

                } catch (Exception e) {
                    logger.error("消息解析管理线程运行异常", e);
                }
            }
        }

    }

    @Override
    public void stop() {
        isRun = false;
        isStart = false;
    }

    public HandlerFactory getHandlerFactory() {
        return handlerFactory;
    }

    public void setHandlerFactory(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }
}
