package com.aisino.service;

import java.util.List;

import com.aisino.domain.News;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;

public class NewsService {

	@CacheName("showNews")
	public List<News> showNews(){
//		return News.news.find("select * from news");
		return News.news.findByCache("zksCache", "showNews", "select * from news");
	}
	
	@CacheName("showNewsPaginate")
	public Page<Record> showNewsPaginate(int pageNo,int pageSize){
		if(CacheKit.get("zksCache", "showNewsPaginate") != null){
			
			System.out.println("----first");
			return Db.paginate(pageNo, pageSize, "select * ", "from news");
		}else{
			System.out.println("----paginateByCache");
			return Db.paginateByCache("zksCache", "showNewsPaginate", pageNo, pageSize, "select * ", "from news");
		}
		
	}
	
	public News getById(int id){
		return News.news.findById(id);
	}
	public boolean updateNews(News news){
		Integer id = news.get("id");
		return News.news._setAttrs(news).update();
	}
	public boolean deleteById(int id){
		return News.news.deleteById(id);
	}
	public boolean saveNews(News news){
		return News.news._setAttrs(news).save();
	}
}
