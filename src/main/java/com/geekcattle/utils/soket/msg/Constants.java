/**
 * Project Name:netty
 * File Name:Constants.java
 * Package Name:com.geekcattle.netty
 * Date:2015年5月11日上午10:01:11
 * Copyright (c) 2015, geekcattle Jenkins All Rights Reserved.
 * 
 *
*/

package com.geekcattle.utils.soket.msg;

import com.geekcattle.utils.utils.PropertiesUtil;

/**
 * ClassName:Constants
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2015年5月11日 上午10:01:11 
 * @author   geekcattle
 * @see 	 
 */
public class Constants {

	/**
	 * 配置文件地址
	 */
	private static final String SERVER_CONFIG= "netty-config.properties";
	private static final String charset="utf-8";
	public static final String SERVER_MAC =PropertiesUtil.getProperties(SERVER_CONFIG, charset).getProperty("server.mac");
	public static final String HEARTBEATDELAY = PropertiesUtil.getProperties().getProperty("tcp.temheartbeatdelay");
	
	public static final int HEAD_LENGTH = 14;
	public static final int SIGN_STAR_LENGTH = 1;
	public static final int SIGN_END_LENGTH = 1;
	public static final int SIGN_LENGTH = 1;
	
	
}

