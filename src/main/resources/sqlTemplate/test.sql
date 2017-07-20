#sql("getProductByConditions")
select * from product p where p.productNo = ?
#end

#sql("getProductByConditions2")
	select * from product p where p.productNo = #para(productNo)
#end

#sql("product1")
	#@queryById("product",id)
#end