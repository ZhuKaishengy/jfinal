package com.aisino.service;

import java.util.List;

import com.aisino.domain.Product;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.ehcache.CacheName;

public class ProductService {
/**
 * 复合主键
 * @param product
 * @return
 */
	@CacheName("showProductById")
	public Product showProductById(Product product){
		String productId = product.get("productId");
		String productNo = product.get("productNo");
		return Product.productModel.findFirstByCache("zksCache", "showProducts", "select * from product where productId = ? and productNo = ?", productId,productNo);
	}
	@CacheName("getProductByConditions")
	public List<Record> getProductByConditions(String no){
		String sql = Db.getSql("getProductByConditions");
		return Db.findByCache("zksCache", "getProductByConditions", sql, no);
	}
	
	public List<Record> getProductByConditions2(String no){
		Kv kv = Kv.by("productNo", no);
		SqlPara sqlPara = Db.getSqlPara("product.getProductByConditions2", kv);
		return Db.find(sqlPara);
	}
	/*public Product getprod(int id){
		
	}*/
}
