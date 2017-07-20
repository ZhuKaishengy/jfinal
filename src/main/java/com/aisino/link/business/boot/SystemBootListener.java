package com.aisino.link.business.boot;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;

import com.aisino.demoConfig.CommonConstant;
import com.jfinal.config.JFinalConfig;


public class SystemBootListener implements ServletContextListener {

	public static JFinalConfig jFinalConfig = null;
	
	public void contextDestroyed(ServletContextEvent arg0) {

		MDC.put("module", "[MAIN]");
		MDC.put("processType", "[SYSTEM-DESTROY]");
		CommonConstant.log.info("-------------------------------");
		CommonConstant.log.info("-----AISINO-JFINAL系统退出-----");
		CommonConstant.log.info("-------------------------------");
		
		MDC.remove("processType");
		MDC.remove("module");

	}

	
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		PropertyConfigurator.configure(CommonConstant.LOG4J_CONFIG_FILE);
		
		MDC.put("module", "[MAIN]");
		MDC.put("processType", "[SYSTEM-INIT]");

		try {
			loadGlobalResource(servletContextEvent);
		} catch (IOException e) {
			CommonConstant.log.error("加载资源信息时出错" + e);
			CommonConstant.log.error("-------------------------------");
			CommonConstant.log.error("---AISINO-JFINAL系统启动失败---");
			CommonConstant.log.error("-------------------------------");

		}

		CommonConstant.log.info("-------------------------------");
		CommonConstant.log.info("---AISINO-JFINAL启动成功---");
		CommonConstant.log.info("-------------------------------");

		MDC.remove("processType");
		MDC.remove("module");
	}

	/**
	 * 加载本系统所需全局资源
	 * 
	 * @return 资源是否加载成功
	 * @throws IOException
	 */
	public void loadGlobalResource(ServletContextEvent servletContextEvent)
			throws IOException {

		CommonConstant.log.info("加载系统所需资源--start");

		/* 获取应用的上下文信息 */
		if (null == jFinalConfig) {
			if (CommonConstant.log.isDebugEnabled()) {
				CommonConstant.log.debug("获取jFinalConfig失败");
			}
		}

		CommonConstant.log.info("成功加载系统所需资源");

		CommonConstant.log.info("加载系统所需资源--end");
	}

	
}
