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

    private static final int DEFAULTSIZE = 4;
    private int mapSize;
    private final ConcurrentHashMap<SessionFutureKey, AbsMsg>[] maps;

    public MsgFutureManager()
    {
        this(DEFAULTSIZE);
    }

    public MsgFutureManager(int mapSize)
    {
        this.mapSize = mapSize;
        maps = new ConcurrentHashMap[mapSize];
        for (int i = 0; i < mapSize; i++)
        {
            maps[i] = new ConcurrentHashMap<SessionFutureKey, AbsMsg>(256);
        }
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

    private long getMapIndex(SessionFutureKey key)
    {
        long pktID = key.getSeq();
        if (pktID < 0)
        {
            pktID = Math.abs(pktID);
        }
        long idx = pktID % mapSize;
        return idx;
    }

    private ConcurrentHashMap<SessionFutureKey, AbsMsg> getMap(SessionFutureKey key)
    {
        if(null != key){

            long idx = getMapIndex(key);
            return maps[(int)idx];
        }
        else{
            return  null;
        }
    }
}
