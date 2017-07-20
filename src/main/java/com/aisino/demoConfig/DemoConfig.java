package com.aisino.demoConfig;

import java.io.IOException;

import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;

import com.aisino.controller.HelloController;
import com.aisino.domain.News;
import com.aisino.domain.Product;
import com.aisino.domain.User;
import com.aisino.interceptor.GlobalActionInterceptor;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log4jLogFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants constants) {
		// TODO Auto-generated method stub
		Prop prop = PropKit.use("constant.properties");
		constants.setDevMode(prop.getBoolean("DevMode"));
		constants.setBaseUploadPath(prop.get("BaseUploadPath"));
		constants.setBaseDownloadPath(prop.get("BaseDownloadPath"));
		constants.setLogFactory(new Log4jLogFactory());
		constants.setI18nDefaultBaseName("aisino");
	}

	public void loadGlobalResource() throws IOException, InstantiationException, IllegalAccessException {

		CommonConstant.log.info("加载系统所需资源--start");

		/* 获取应用的上下文信息 */
		if (null == DemoConfig.class.newInstance()) {
			if (CommonConstant.log.isDebugEnabled()) {
				CommonConstant.log.debug("获取jFinalConfig失败");
			}
		}

		CommonConstant.log.info("成功加载系统所需资源");

		CommonConstant.log.info("加载系统所需资源--end");
	}

	public void afterJFinalStart() {
		PropertyConfigurator.configure(CommonConstant.LOG4J_CONFIG_FILE);

		MDC.put("module", "[MAIN]");
		MDC.put("processType", "[SYSTEM-INIT]");

		try {
			this.loadGlobalResource();
		} catch (Exception e) {
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

	public void beforeJFinalStop() {
		MDC.put("module", "[MAIN]");
		MDC.put("processType", "[SYSTEM-DESTROY]");
		CommonConstant.log.info("-------------------------------");
		CommonConstant.log.info("-----AISINO-JFINAL系统退出-----");
		CommonConstant.log.info("-------------------------------");

		MDC.remove("processType");
		MDC.remove("module");

	}

	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub
		// finalView = baseViewPath + viewPath + view
		routes.setBaseViewPath("/pages");
		routes.add("/hello", HelloController.class, "");
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
		interceptors.add(new TxByMethodRegex("(.*save.*|.*update.*|.*delete.*)"));
	}

	@Override
	public void configPlugin(Plugins plugins) {
		// TODO Auto-generated method stub
		// 二级缓存插件
		plugins.add(new EhCachePlugin());
		// c3p0插件
		Prop prop = PropKit.use("db.txt");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(prop.get("sqlserver.jdbcUrl"), prop.get("sqlserver.user"),
				prop.get("sqlserver.password"), prop.get("sqlserver.driverClass"));
		plugins.add(c3p0Plugin);
		// ActiveRecordPlugin 插件
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin("mssql", c3p0Plugin);
		// 设置方言很重要，否则项目启动报错
		// 设置方言，显示sql，sql模板位置
		activeRecordPlugin.setDialect(new SqlServerDialect()).setShowSql(true)
				.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "\\sqlTemplate");
		plugins.add(activeRecordPlugin);
		// sql模板映射
		activeRecordPlugin.addSqlTemplate("all.sql");
		// 数据库映射
		activeRecordPlugin.addMapping("news", News.class).addMapping("product", "productId,productNo", Product.class);
		// -----------------------------------------------------------------------
		// 多数据源的配置(oracle)
		C3p0Plugin c3p0Plugin2 = new C3p0Plugin(prop.get("oracle.jdbcUrl"), prop.get("oracle.user"),
				prop.get("oracle.password"), prop.get("oracle.driverClass"));
		plugins.add(c3p0Plugin2);
		// ActiveRecordPlugin 插件
		ActiveRecordPlugin activeRecordPlugin2 = new ActiveRecordPlugin("oracle", c3p0Plugin2);
		// 设置方言很重要，否则项目启动报错
		activeRecordPlugin2.setDialect(new OracleDialect()).setShowSql(true).setTransactionLevel(8)
				.setContainerFactory(new CaseInsensitiveContainerFactory());
		plugins.add(activeRecordPlugin2);
		activeRecordPlugin2.addMapping("TEST_USER", User.class);
		// -------------------------------------------------------------------------
		// redis插件
		/*
		 * RedisPlugin redisPlugin = new RedisPlugin("redisCache", "host",
		 * "post"); plugins.add(redisPlugin);
		 */
		// -----------------------------------------------------------
		// cron4j 任务调度
		Cron4jPlugin cron4jPlugin = new Cron4jPlugin(PropKit.use("cron4j.properties"), "cron4j");
		plugins.add(cron4jPlugin);
	}
}