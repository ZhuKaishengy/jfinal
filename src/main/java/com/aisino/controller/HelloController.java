package com.aisino.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.aisino.demoConfig.CommonConstant;
import com.aisino.domain.News;
import com.aisino.domain.Product;
import com.aisino.domain.User;
import com.aisino.service.NewsService;
import com.aisino.service.ProductService;
import com.aisino.service.UserService;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
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
		NewsService newsService = Duang.duang(NewsService.class);
		List<News> list = newsService.showNews();
		setAttr("list", list);
		renderJsp("news.jsp");
	}
	@ActionKey(value="/fileUpload")
	public void fileUpload(){
		UploadFile file = getFile();
		String fileName = file.getFileName();
		String username = getPara("username");
//		renderText("username"+username+"\n"+"fileName"+fileName);
		renderFile(fileName);
	}
/*	@Clear
	public void canLogin(){
		User user = getModel(User.class);
		UserService userService = Duang.duang(UserService.class);
		String canLogin = userService.canLogin(user);
		System.out.println(canLogin);
		renderText(canLogin);
	}*/
	
	
	public void showNewsPaginate(){
		int pageNo = getParaToInt("pageNo");
		int pageSize = getParaToInt("pageSize");
		NewsService newsService = Duang.duang(NewsService.class);
		Page<Record> showNewsPaginate = newsService.showNewsPaginate(pageNo, pageSize);
		Map<String, Object> attrmap = new HashMap<String, Object>();
		attrmap.put("pageNo", showNewsPaginate.getPageNumber());
		attrmap.put("pageSize", showNewsPaginate.getPageSize());
		attrmap.put("list", showNewsPaginate.getList());
		setAttr("attrmap", attrmap);
		renderJsp("news3.jsp");
	}
	public void updateAndDelete(){
		NewsService newsService = Duang.duang(NewsService.class);
		News news = new News();
		News news2 = new News();
		news.set("id", 1);
		news.set("title", "zks");
		news.set("content","sjx");
		news2.set("id", 1);
		news2.set("title", "zks");
		news2.set("content","haha");
		newsService.updateNews(news);
//		boolean b = newsService.deleteById(6);
		boolean b = newsService.saveNews(news2);
		System.out.println(b);
		renderText("zks");
	}
	
	public void showProductById(){
		String productId = getPara(0);
		String productNo = getPara(1);
		Product product = new Product();
		product.set("productNo", productNo).set("productId", productId);
		ProductService productService = new ProductService();
		Product product2 = productService.showProductById(product);
		setAttr("product", product2);
		renderJsp("product.jsp");;
	}
	
	@ActionKey("/getAndSaveUser")
	public void getAndSaveUser(){
		MDC.put("model", "getAndSaveUser");
		UserService userService = Duang.duang(UserService.class);
		User user = new User().set("id", "se_zks.nextval").set("no", "1002").set("name", "sjx").set("password", "456");
		userService.saveUser(user);
		List<User> users = userService.getUsers();
		CommonConstant.log.info(users.toString());
		setAttr("users", users);
		renderJsp("user.jsp");
	}
	@ActionKey("getProductByConditions")
	public void getProductByConditions(){
		ProductService productService = Duang.duang(ProductService.class);
		List<Record> list = productService.getProductByConditions("200");
		setAttr("products", list);
		renderJsp("products.jsp");
	}
	@ActionKey("/getProductByConditions2")
	public void getProductByConditions2(){
		System.out.println("---->");
		ProductService productService = Duang.duang(ProductService.class);
		List<Record> list = productService.getProductByConditions2("200");
		CommonConstant.log.info(list.toString());
		setAttr("products", list);
		renderJsp("products.jsp");
	}
	@ActionKey("/getMessage")
	public void getMessage(){
		MDC.put("module", "[HelloController]");
		MDC.put("processType", "[getMessage]");
//		Res res = I18n.use("en_US");
		Res res = I18n.use("zh_CN");
//		String mess = res.get("message");
		String mess = res.format("message", "zks",new Date());
		CommonConstant.log.info(mess);
		renderNull();
	}
}
