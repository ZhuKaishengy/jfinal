package com.aisino.demoConfig;

import com.aisino.controller.HelloController;
import com.aisino.domain.News;
import com.aisino.interceptor.GlobalActionInterceptor;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants constants) {
		// TODO Auto-generated method stub
		constants.setDevMode(true);
		constants.setBaseUploadPath("C:\\mydownloads\\myeclipse2016\\aboutSystem\\configFile\\TestJfinal-1\\uploadfiles");
		constants.setBaseDownloadPath("C:\\mydownloads\\myeclipse2016\\aboutSystem\\configFile\\TestJfinal-1\\uploadfiles");
	}
	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub
		//finalView = baseViewPath + viewPath + view
		routes.setBaseViewPath("/views");
		routes.add("/hello", HelloController.class,"");
	}
	@Override
	public void configEngine(Engine engine) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void configHandler(Handlers handlers) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void configInterceptor(Interceptors interceptors) {
		// TODO Auto-generated method stub
		interceptors.addGlobalActionInterceptor(new GlobalActionInterceptor());
	}
	@Override
	public void configPlugin(Plugins plugins) {
		// TODO Auto-generated method stub
//		loadPropertyFile("db.txt");
		Prop prop = PropKit.use("db.txt");
		DruidPlugin druidPlugin = new DruidPlugin(prop.get("jdbcUrl"),prop.get("user"), prop.get("password"));
		plugins.add(druidPlugin);
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
		//设置方言很重要，否则项目启动报错
		activeRecordPlugin.setDialect(new SqlServerDialect());
		plugins.add(activeRecordPlugin);
		activeRecordPlugin.addMapping("news", News.class);
	}
}
