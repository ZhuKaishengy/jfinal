package com.aisino.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class Tx implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.out.println("事务开启");
		inv.invoke();
		System.out.println("事务结束");
	}

	
}
