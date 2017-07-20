package com.aisino.kit;

import com.jfinal.log.ILogFactory;
import com.jfinal.log.Log;
import com.jfinal.log.Log4jLogFactory;

public class MyLog extends Log{

	public static ILogFactory defaultLogFactory = new Log4jLogFactory();
	
	public static Log getLog(Class<?> clazz) {
		return  defaultLogFactory.getLog(clazz);
	}

	public static Log getLog(String name) {
		return defaultLogFactory.getLog(name);
	}
	
	@Override
	public void debug(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String s, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String s, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatal(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatal(String s, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String s, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void warn(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String s, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

}
