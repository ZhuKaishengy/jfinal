package com.aisino.demoConfig;

import java.io.File;

import com.jfinal.log.Log;

public class CommonConstant {

	private static final String CONFIG_NAME = "config";

	private static final String SERVER_HOME = "com.aisino.mid.home";

	private static final String LOG4J_FILE = "log4j.properties";

	public static final String HOME_DIR;

	public static final String CONFIG_DIR;

	public static final String LOG4J_CONFIG_FILE;

	public static final String MODULE_NAME = "aisino-jfinal";

	public static final String FILE_SEPARATOR ;

	public static final Log log = Log.getLog(CommonConstant.class);
	
	static {
		FILE_SEPARATOR = File.separator;
		String home = System.getProperty(SERVER_HOME);
		HOME_DIR = null == home ? System.getProperty("user.dir") : home;
		CONFIG_DIR = HOME_DIR + FILE_SEPARATOR + MODULE_NAME + FILE_SEPARATOR
				+ CONFIG_NAME + FILE_SEPARATOR;
		LOG4J_CONFIG_FILE = CONFIG_DIR + LOG4J_FILE;
	}
}
