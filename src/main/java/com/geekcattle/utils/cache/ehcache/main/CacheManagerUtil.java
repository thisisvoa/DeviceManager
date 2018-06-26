/**
 * Project Name:main
 * File Name:CacheManagerUtil.java
 * Package Name:com.hdsx.taxi.driver.cq.cache
 * Date:2014年4月9日下午12:49:55
 * Copyright (c) 2014, geekcattle Jenkins All Rights Reserved.
 * 
 *
*/

package com.geekcattle.utils.cache.ehcache.main;


import com.geekcattle.utils.utils.LogUtil;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.guava.GuavaCacheManager;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/**
 * ClassName:CacheManagerUtil
 * Date:     2014年4月9日 下午12:49:55 
 * @author   geekcattle
 * @see 	 
 */
public class CacheManagerUtil {

	private GuavaCacheManager cm = null;
	

			;

	private volatile static CacheManagerUtil singleton = null;
	
	public static CacheManagerUtil getSingletonInstance() {

		if (singleton == null) {
			synchronized (CacheManagerUtil.class) {
				if (singleton == null) {
					singleton = new CacheManagerUtil();
				}
			}
			singleton = new CacheManagerUtil();
		}
		return singleton;
	}

	private CacheManagerUtil() {
		if (LogUtil.getInstance().getLogger(CacheManagerUtil.class).isDebugEnabled()) {
			LogUtil.getInstance().getLogger(CacheManagerUtil.class).debug("CacheManagerUtil() - start path:"); //$NON-NLS-1$
		}

		this.cm = new GuavaCacheManager("msgcache");
		CacheBuilder<Object,Object> cacheBuilder = CacheBuilder.newBuilder();
		cacheBuilder.expireAfterWrite(200, TimeUnit.SECONDS);
		cacheBuilder.maximumSize(500);
		cm.setCacheBuilder(cacheBuilder);

		if (LogUtil.getInstance().getLogger(CacheManagerUtil.class).isDebugEnabled()) {
			LogUtil.getInstance().getLogger(CacheManagerUtil.class).debug("CacheManagerUtil() - end"); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * getCache:(获取缓存对象). 
	 *
	 * @author geekcattle
	 * @param name
	 * @return
	 */
	public Cache getCache(String name){
		return this.cm.getCache(name);
	}
	/**
	 * 
	 * put:(在指定缓存对象中加入需要缓存的对象). 
	 *
	 * @author geekcattle
	 * @param cacheName
	 * @param key
	 * @param value
	 */
    public void put(String cacheName, String key, Object value) {
        Cache cache = this.cm.getCache(cacheName);
        cache.put(key,value);
    }  
  
    /**
     * 
     * get:(根据key从指定缓存对象中获取对象). 
     *
     * @author geekcattle
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {
		Cache cache = this.cm.getCache(cacheName);
		Object var = cache.get(key, new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				System.out.println("如果没有值,就执行其他方式去获取值");
				String var = "Google.com.sg";
				return var;
			}
		});
		return var;
	}

	/**
     * 
     * remove:(从指定缓存对象中清除对象). 
     *
     * @author geekcattle
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key) {  
        Cache cache = this.cm.getCache(cacheName);
        cache.evict(key);
    }
    
    /**
     * 
     * getKeys:(获取keys列表). 
     *
     * @author geekcattle
     * @param cacheName
     * @return
     */
	@SuppressWarnings("rawtypes")
	public Object getKeys(String cacheName){
        Cache cache = this.cm.getCache(cacheName);
        return cache.getNativeCache();
    }
	
	/**
	 * 
	 * containsKey:(判断消息是否存在). 
	 *
	 * @author geekcattle
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Boolean containsKey(String cacheName,String key){
        Cache cache = this.cm.getCache(cacheName);
		Cache.ValueWrapper element = cache.get(key);
        return element == null ? false : true;  
	}
}

