###自定义sql模板

#define queryById(table,id)
select * from #(table) where id = #(id)
#end

#define queryForList(table)
select * from #(table)
#end

#define queryForListByConditions(table)
select * from #(table) 
	#for(x:cond)
		#(for.index == 0? "where" : "and") #(x.key) #para(x.value)
	#end
#end