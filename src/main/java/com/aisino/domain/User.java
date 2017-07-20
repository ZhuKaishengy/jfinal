package com.aisino.domain;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {

	private static final long serialVersionUID = 1L;
	public static final User userDao = new User();
}
