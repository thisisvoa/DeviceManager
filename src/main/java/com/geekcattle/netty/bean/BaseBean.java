/**
 * Project Name:bean
 * File Name:BaseBean.java
 * Package Name:com.hdsx.taxi.upa.bean
 * Date:2014年10月23日上午8:59:20
 * Copyright (c) 2014, geekcattle Jenkins All Rights Reserved.
 * 
 *
*/

package com.geekcattle.netty.bean;

import java.util.HashMap;

/**
 * ClassName:BaseBean
 * Date:     2014年10月23日 上午8:59:20 
 * @author   geekcattle
 * @see 	 
 */
public abstract class BaseBean {
	
	/**
	 * 
	 * getMapId:(返回组成map的字符串). 
	 *
	 * @author geekcattle
	 * @return
	 */
	public abstract HashMap<String, Object> getMapId();
}

