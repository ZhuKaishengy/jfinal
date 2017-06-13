package com.aisino.controller;

import com.aisino.domain.News;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

public class HelloController extends Controller {

	public void index(){
		renderText("hello jfinal");
	}
	@Clear
	public void execute(){
		String name = getPara(0);
		renderText("hello:"+name);
	}
	public void test(){
		renderJsp("/index.jsp");
	}
	public void login(){
		renderJsp("login.jsp");
	}
	@ActionKey(value="/showname")
	public void show(){
		renderJsp("login.jsp");
	}
	public void showNews(){
		News news = getModel(News.class);
		System.out.println(news);
		setAttr("news", news);
		renderJsp("login.jsp");
	}
	@ActionKey(value="/fileUpload")
	public void fileUpload(){
		UploadFile file = getFile();
		String fileName = file.getFileName();
		String username = getPara("username");
//		renderText("username"+username+"\n"+"fileName"+fileName);
		renderFile(fileName);
	}
}
