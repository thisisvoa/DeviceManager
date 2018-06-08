package com.geekcattle.netty.msg;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/2/26/026.
 */
@Component
@Qualifier("msgFutureManager")
public class MsgFutureManager {

    private static final int DEFAULTSIZE = 1;
    private int mapSize;
    private final ConcurrentHashMap<SessionFutureKey, AbsMsg> maps=new ConcurrentHashMap<SessionFutureKey, AbsMsg>(256);

    public MsgFutureManager()
    {
        this(DEFAULTSIZE);
    }

    public MsgFutureManager(int mapSize)
    {
        this.mapSize = mapSize;

    }

    public AbsMsg put(SessionFutureKey key, AbsMsg future)
    {
        ConcurrentHashMap<SessionFutureKey, AbsMsg> futureMap = getMap(key);
        return futureMap.put(key, future);
    }

    public AbsMsg get(SessionFutureKey key)
    {
        ConcurrentHashMap<SessionFutureKey, AbsMsg> futureMap = getMap(key);
        return futureMap.get(key);
    }

    public AbsMsg remove(SessionFutureKey key)
    {
        ConcurrentHashMap<SessionFutureKey, AbsMsg> futureMap = getMap(key);
        return futureMap.remove(key);
    }

   /* private long getMapIndex(SessionFutureKey key)
    {
        long pktID = key.getSeq();
        if (pktID < 0)
        {
            pktID = Math.abs(pktID);
        }
        long idx = pktID % mapSize;
        return idx;
    }*/

    private ConcurrentHashMap<SessionFutureKey, AbsMsg> getMap(SessionFutureKey key)
    {
        if(null != key){

         //   long idx = getMapIndex(key);
            return maps;  //默认一个currentHashMap，这个是hi导致内存不能释放的原因
        }
        else{
            return  null;
        }
    }

    public   void clearCurrentHashMap(){
        maps.clear();
    }
}
