package com.aisino.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GlobalActionInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.out.println("拦截开始！");
		inv.invoke();
		System.out.println("拦截结束！");
	}

}
