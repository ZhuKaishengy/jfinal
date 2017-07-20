package com.aisino.kit;

import com.jfinal.log.Log;
import com.jfinal.log.Log4jLogFactory;

public class MyLog4jLogFactory extends Log4jLogFactory {

	public Log getLog(Class<?> clazz) {
		return MyLog.getLog(clazz);
	}

	public Log getLog(String name) {
		return MyLog.getLog(name);
	}
}
