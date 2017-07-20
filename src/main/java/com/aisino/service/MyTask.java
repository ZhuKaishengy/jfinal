package com.aisino.service;

import com.jfinal.plugin.activerecord.Db;

public class MyTask implements Runnable{

	/**
	 * 创建定时任务用于数据表的备份
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Db.update("SELECT * INTO usernew FROM [user]");
	}

}
