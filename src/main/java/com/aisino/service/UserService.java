package com.aisino.service;

import java.util.List;

import com.aisino.domain.User;
import com.jfinal.plugin.ehcache.CacheName;

public class UserService {

	@CacheName("getUsers")
	public List<User> getUsers(){
		return User.userDao.findByCache("zksCache", "getUsers", "select * from TEST_USER");
	}
	
	public boolean saveUser(User user){
		return User.userDao._setAttrs(user).save();
	}
}
