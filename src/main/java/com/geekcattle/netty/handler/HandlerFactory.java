package com.geekcattle.netty.handler;

import com.geekcattle.netty.msg.AbsMsg;
import com.geekcattle.netty.msg.MessageID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * handler工厂
 *
 * @author geekcattle
 */
public class HandlerFactory {

    private static final Logger logger = LoggerFactory
            .getLogger(HandlerFactory.class);

    @Autowired
    private Handler0x0001 handler0x0001;

    @Autowired
    private Handler0x0002 handler0x0002;

    @Autowired
    private Handler0x0003 handler0x0003;
    @Autowired
    private Handler0x2001 handler0x2001;

    public IHandler getHandler(AbsMsg m) {
        int msgid = m.getHead().getMsgid();
        if (logger.isDebugEnabled()) {
            logger.debug("handler工厂产生消息:" + Integer.toHexString(msgid)); //$NON-NLS-1$
        }
        IHandler h = null;
        switch (msgid) {
            case MessageID.MSG_0x0001:
                h = handler0x0001;
                break;
            case MessageID.MSG_0x0002:
                h = handler0x0002;
                break;
            case MessageID.MSG_0x0003:
                h = handler0x0003;
                break;
            case MessageID.MSG_0x2001:
                h = handler0x2001;
                break;

            default:
                break;
        }
        return h;
    }

    public Handler0x0001 getHandler0x0001() {
        return handler0x0001;
    }

    public void setHandler0x0001(Handler0x0001 handler0x0001) {
        this.handler0x0001 = handler0x0001;
    }

    public Handler0x0002 getHandler0x0002() {
        return handler0x0002;
    }

    public void setHandler0x0002(Handler0x0002 handler0x0002) {
        this.handler0x0002 = handler0x0002;
    }

    public Handler0x0003 getHandler0x0003() {
        return handler0x0003;
    }

    public void setHandler0x0003(Handler0x0003 handler0x0003) {
        this.handler0x0003 = handler0x0003;
    }

    public Handler0x2001 getHandler0x2001() {
        return handler0x2001;
    }

    public void setHandler0x2001(Handler0x2001 handler0x2001) {
        this.handler0x2001 = handler0x2001;
    }
}
